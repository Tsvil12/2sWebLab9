package com.example.springflyway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
open class SpringFlywayApplication

fun main(args: Array<String>) {
    runApplication<SpringFlywayApplication>(*args)
}