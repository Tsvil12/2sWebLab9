package com.example.springflyway.adapter.web

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.springframework.boot.testcontainers.service.connection.ServiceConnection

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
class RestaurantIntegrationTest {

    companion object {
        @Container
        @ServiceConnection
        val postgres = PostgreSQLContainer<Nothing>("postgres:16-alpine").apply {
            withDatabaseName("testdb")
            withUsername("test")
            withPassword("test")
        }
    }

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `create restaurant returns 201`() {
        mockMvc.perform(
            post("/api/v1/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "New Place", "address": "Test str, 1"}""")
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name").value("New Place"))
            .andExpect(jsonPath("$.address").value("Test str, 1"))
    }

    @Test
    fun `get restaurant by id returns 200`() {
        val response = mockMvc.perform(
            post("/api/v1/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "Pizza Place", "address": "Lenina str, 1"}""")
        ).andExpect(status().isCreated).andReturn()

        val id = objectMapper.readTree(response.response.contentAsString).get("id").asLong()

        mockMvc.perform(get("/api/v1/restaurants/$id"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("Pizza Place"))
            .andExpect(jsonPath("$.address").value("Lenina str, 1"))
    }

    @Test
    fun `get non-existent restaurant returns 404`() {
        mockMvc.perform(get("/api/v1/restaurants/999999"))
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.message").value("Ресторан с id=999999 не найден"))
    }

    @Test
    fun `create restaurant with empty name returns 400 with validation errors`() {
        mockMvc.perform(
            post("/api/v1/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "", "address": "Test str, 1"}""")
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errors.name").exists())
    }

    @Test
    fun `create duplicate restaurant returns 409`() {
        mockMvc.perform(
            post("/api/v1/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "Duplicate", "address": "Test str, 1"}""")
        ).andExpect(status().isCreated)

        mockMvc.perform(
            post("/api/v1/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"name": "Duplicate", "address": "Test str, 2"}""")
        )
            .andExpect(status().isConflict)
            .andExpect(jsonPath("$.message").value("Ресторан с именем 'Duplicate' уже существует"))
    }
}