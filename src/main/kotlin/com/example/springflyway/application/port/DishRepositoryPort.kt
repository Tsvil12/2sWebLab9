package com.example.springflyway.application.port

import com.example.springflyway.adapter.out.persistence.entity.DishEntity

interface DishRepositoryPort {
    fun save(dish: DishEntity): DishEntity
    fun findById(id: Long): DishEntity?
    fun findAll(): List<DishEntity>
    fun findByNameContaining(namePart: String): List<DishEntity>
    fun findByRestaurantId(restaurantId: Long): List<DishEntity>
    fun update(dish: DishEntity): DishEntity
    fun deleteById(id: Long)
}