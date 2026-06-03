package com.example.springflyway.adapter.web

import com.example.springflyway.adapter.web.dto.RestaurantDto
import com.example.springflyway.application.service.RestaurantService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/restaurants")
@Tag(name = "Restaurants", description = "Управление ресторанами")
open class RestaurantController(
    private val service: RestaurantService
) {

    @GetMapping
    @Operation(summary = "Получить список всех ресторанов")
    fun getAll(): List<RestaurantDto> = service.findAll().map { RestaurantMapper.toDto(it) }

    @GetMapping("/{id}")
    @Operation(summary = "Получить ресторан по ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Ресторан найден"),
            ApiResponse(responseCode = "404", description = "Ресторан не найден")
        ]
    )
    fun getById(@PathVariable id: Long): ResponseEntity<RestaurantDto> {
        val entity = service.findById(id)
        return ResponseEntity.ok(RestaurantMapper.toDto(entity))
    }

    @PostMapping
    @Operation(summary = "Создать новый ресторан (только ADMIN)")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Ресторан создан"),
            ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            ApiResponse(responseCode = "401", description = "Не авторизован"),
            ApiResponse(responseCode = "403", description = "Доступ запрещён (нужна роль ADMIN)")
        ]
    )
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@Valid @RequestBody dto: RestaurantDto): ResponseEntity<RestaurantDto> {
        val entity = RestaurantMapper.toEntity(dto)
        val saved = service.create(entity)
        return ResponseEntity.status(HttpStatus.CREATED).body(RestaurantMapper.toDto(saved))
    }
}