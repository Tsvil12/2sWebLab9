package com.example.springflyway

import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import kotlin.test.assertTrue

class SimpleContainerTest {

    @Test
    fun `container should start`() {
        val postgres = PostgreSQLContainer<Nothing>("postgres:16-alpine").apply {
            withDatabaseName("test")
            withUsername("test")
            withPassword("test")
            start()
        }
        assertTrue(postgres.isRunning)
        println("Postgres running at: ${postgres.jdbcUrl}")
        postgres.stop()
    }
}