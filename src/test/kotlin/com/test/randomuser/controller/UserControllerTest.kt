package com.test.randomuser.controller

import com.test.randomuser.entity.User
import com.test.randomuser.service.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.WebTestClient.ListBodySpec
import reactor.core.publisher.Mono


@WebFluxTest(UserController::class)
class UserControllerTest(
    @Autowired private val webTestClient: WebTestClient,
) {
    @MockBean
    private lateinit var userService: UserService

    @Test
    fun `should return list of users`() {
        val responseBody = mutableListOf(
            User(
                firstName = "آیناز",
                lastName = "کریمی",
                gender = "female",
                email = "aynz.khrymy@example.com"
            )
        )

        given(userService.getUserWithSeed("foobar"))
            .willReturn(Mono.fromCallable { responseBody })

        webTestClient.get().uri("/v1/users/foobar")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(User::class.java)
            .consumeWith<ListBodySpec<User>> { user ->
                Assertions.assertEquals(responseBody, user.responseBody)
            }
    }
}