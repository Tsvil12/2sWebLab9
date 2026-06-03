package com.example.springflyway.adapter.web

import com.example.springflyway.adapter.web.dto.DishDto
import com.example.springflyway.application.service.DishService
import com.example.springflyway.application.service.RestaurantService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
open class DishController(
    private val dishService: DishService,
    private val restaurantService: RestaurantService
) {
    @PostMapping("/restaurants/{restaurantId}/dishes")
    @PreAuthorize("hasRole('ADMIN')")
    fun createDish(
        @PathVariable restaurantId: Long,
        @Valid @RequestBody dto: DishDto
    ): ResponseEntity<DishDto> {
        val restaurant = restaurantService.findById(restaurantId)
        val dish = DishMapper.toEntity(dto, restaurant)
        val saved = dishService.create(dish)
        return ResponseEntity.status(HttpStatus.CREATED).body(DishMapper.toDto(saved))
    }

    @GetMapping("/restaurants/{restaurantId}/dishes")
    fun getRestaurantDishes(@PathVariable restaurantId: Long): List<DishDto> {
        return dishService.findByRestaurantId(restaurantId).map { DishMapper.toDto(it) }
    }

    @GetMapping("/dishes")
    fun getAllDishes(@RequestParam(required = false) namePart: String?): List<DishDto> {
        return dishService.findAll(namePart).map { DishMapper.toDto(it) }
    }

    @GetMapping("/dishes/{id}")
    fun getDishById(@PathVariable id: Long): ResponseEntity<DishDto> {
        val dish = dishService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(DishMapper.toDto(dish))
    }

    @PutMapping("/dishes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun updateDish(@PathVariable id: Long, @RequestBody dto: DishDto): ResponseEntity<DishDto> {
        val restaurant = restaurantService.findById(dto.restaurantId)
        val dish = DishMapper.toEntity(dto.copy(id = id), restaurant)
        val updated = dishService.update(dish)
        return ResponseEntity.ok(DishMapper.toDto(updated))
    }

    @DeleteMapping("/dishes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteDish(@PathVariable id: Long): ResponseEntity<Void> {
        dishService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}