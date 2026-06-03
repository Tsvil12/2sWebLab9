package com.example.springflyway.adapter.web

import com.example.springflyway.adapter.web.dto.RestaurantDto
import com.example.springflyway.application.service.RestaurantService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/restaurants")
class RestaurantController(
    private val service: RestaurantService
) {
    @PostMapping
    fun create(@Valid @RequestBody dto: RestaurantDto): ResponseEntity<RestaurantDto> {
        val entity = RestaurantMapper.toEntity(dto)
        val saved = service.create(entity)
        return ResponseEntity.status(HttpStatus.CREATED).body(RestaurantMapper.toDto(saved))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<RestaurantDto> {
        val entity = service.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(RestaurantMapper.toDto(entity))
    }

    @GetMapping
    fun getAll(): List<RestaurantDto> = service.findAll().map { RestaurantMapper.toDto(it) }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: RestaurantDto): ResponseEntity<RestaurantDto> {
        if (service.findById(id) == null) return ResponseEntity.notFound().build()
        val entity = RestaurantMapper.toEntity(dto.copy(id = id))
        val updated = service.update(entity)
        return ResponseEntity.ok(RestaurantMapper.toDto(updated))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        if (service.findById(id) == null) return ResponseEntity.notFound().build()
        service.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}