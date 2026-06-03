package com.example.springflyway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SpringFlywayApplication

fun main(args: Array<String>) {
    runApplication<SpringFlywayApplication>(*args)
}