package com.test.randomuser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RandomUserApplication

fun main(args: Array<String>) {
    runApplication<RandomUserApplication>(*args)
}
