package com.test.randomuser.controller

import com.test.randomuser.entity.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `should return list of user`() {
        webTestClient.get().uri("/v1/users/foobar")
            .header(HttpHeaders.ACCEPT, "application/json")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(User::class.java)
    }
}