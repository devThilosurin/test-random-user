package com.test.randomuser.client

import com.test.randomuser.entity.User
import com.test.randomuser.entity.UserClientResponse
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Configuration
class UserClient(
    private val webClient: WebClient,
) {
    companion object {
        const val BASE_URL = "https://randomuser.me/api/"
    }

    fun getUser(seed: String) =
        webClient.get()
            .uri("$BASE_URL/?seed=$seed")
            .retrieve()
            .bodyToMono(UserClientResponse::class.java)
            .flatMapMany {
                if (it.results.isNullOrEmpty()) {
                    Flux.empty()
                } else {
                    Flux.fromIterable(it.results)
                }
            }
            .reduce(mutableListOf<User>()) { acc, user ->
                acc.add(
                    User(
                        firstName = user?.name?.first ?: "",
                        lastName = user?.name?.last ?: "",
                        gender = user?.gender ?: "",
                        email = user?.email ?: "",
                    )
                )
                acc
            }
}