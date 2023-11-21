package com.test.randomuser.controller

import com.test.randomuser.service.UserService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserController(private val userService: UserService) {

    @RequestMapping("/{seed}")
    fun getUserBySeed(@PathVariable seed: String) =
        userService.getUserWithSeed(seed)
}