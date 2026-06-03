package com.example.springflyway.application.service

import com.example.springflyway.adapter.out.persistence.entity.RestaurantEntity
import com.example.springflyway.application.exception.AlreadyExistsException
import com.example.springflyway.application.exception.NotFoundException
import com.example.springflyway.application.port.RestaurantRepositoryPort
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class RestaurantServiceTest {

    @MockK
    lateinit var repository: RestaurantRepositoryPort

    @InjectMockKs
    lateinit var service: RestaurantService

    @Test
    fun `getById returns restaurant when exists`() {
        val entity = RestaurantEntity(id = 1L, name = "Pizza Place", address = "Lenina str, 1")
        every { repository.findById(1L) } returns entity

        val result = service.findById(1L)

        assertEquals(1L, result.id)
        assertEquals("Pizza Place", result.name)
        verify(exactly = 1) { repository.findById(1L) }
    }

    @Test
    fun `getById throws NotFoundException when not exists`() {
        every { repository.findById(999L) } returns null

        assertThrows<NotFoundException> {
            service.findById(999L)
        }
    }

    @Test
    fun `create throws AlreadyExistsException when name duplicates`() {
        every { repository.existsByName("Pizza Place") } returns true

        assertThrows<AlreadyExistsException> {
            service.create(RestaurantEntity(name = "Pizza Place", address = "Mira str, 5"))
        }

        verify(exactly = 0) { repository.save(any()) }
    }

    @Test
    fun `create successfully creates restaurant`() {
        val newRestaurant = RestaurantEntity(name = "New Place", address = "Test str, 1")
        val savedRestaurant = newRestaurant.copy(id = 1L)

        every { repository.existsByName("New Place") } returns false
        every { repository.save(newRestaurant) } returns savedRestaurant

        val result = service.create(newRestaurant)

        assertEquals(1L, result.id)
        assertEquals("New Place", result.name)
        verify(exactly = 1) { repository.save(newRestaurant) }
    }
}