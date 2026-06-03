package com.example.springflyway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = ["com.example.springflyway.adapter.out.persistence"])
open class SpringFlywayApplication

fun main(args: Array<String>) {
    runApplication<SpringFlywayApplication>(*args)
}