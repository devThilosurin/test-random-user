package com.test.randomuser

import com.test.randomuser.client.UserClient
import com.test.randomuser.controller.UserController
import com.test.randomuser.entity.User
import com.test.randomuser.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpHeaders
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.client.WebClient

@WebFluxTest(controllers = [UserController::class])
@Import(WebClient::class, UserClient::class, UserService::class)
class UserControllerTest {
    companion object {
        private val webTestClient = WebTestClient.bindToController(UserControllerTest()).build()
    }

    @Test
    fun getUserBySeed() {
        webTestClient.get()
            .uri("/v1/users/foobar")
            .header(HttpHeaders.ACCEPT, "application/json")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(User::class.java)
    }
}