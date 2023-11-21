package com.test.randomuser.service

import com.test.randomuser.client.UserClient
import com.test.randomuser.util.LoggerDelegate
import org.springframework.stereotype.Service


@Service
class UserService(private val userClient: UserClient) {
    companion object {
        private val log by LoggerDelegate()
    }

    fun getUserWithSeed(seed: String) =
        userClient.getUser(seed).doOnNext(log::info)
}