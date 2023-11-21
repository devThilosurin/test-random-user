package com.test.randomuser

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RandomUserApplicationTests {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `should return list of user`() {
    }
}
