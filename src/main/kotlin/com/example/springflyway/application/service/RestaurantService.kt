package com.example.springflyway.application.service

import com.example.springflyway.adapter.out.persistence.entity.RestaurantEntity
import com.example.springflyway.application.port.RestaurantRepositoryPort
import org.springframework.stereotype.Service

@Service
class RestaurantService(
    private val repository: RestaurantRepositoryPort
) {
    fun create(restaurant: RestaurantEntity): RestaurantEntity = repository.save(restaurant)
    fun findById(id: Long): RestaurantEntity? = repository.findById(id)
    fun findAll(): List<RestaurantEntity> = repository.findAll()
    fun update(restaurant: RestaurantEntity): RestaurantEntity = repository.update(restaurant)
    fun deleteById(id: Long) = repository.deleteById(id)
}