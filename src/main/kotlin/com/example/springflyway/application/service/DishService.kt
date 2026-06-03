package com.example.springflyway.application.service

import com.example.springflyway.adapter.out.persistence.entity.DishEntity
import com.example.springflyway.application.port.DishRepositoryPort
import org.springframework.stereotype.Service

@Service
class DishService(
    private val repository: DishRepositoryPort
) {
    fun create(dish: DishEntity): DishEntity = repository.save(dish)
    fun findById(id: Long): DishEntity? = repository.findById(id)
    fun findAll(namePart: String?): List<DishEntity> {
        return if (namePart != null) repository.findByNameContaining(namePart)
        else repository.findAll()
    }
    fun findByRestaurantId(restaurantId: Long): List<DishEntity> = repository.findByRestaurantId(restaurantId)
    fun update(dish: DishEntity): DishEntity = repository.update(dish)
    fun deleteById(id: Long) = repository.deleteById(id)
}