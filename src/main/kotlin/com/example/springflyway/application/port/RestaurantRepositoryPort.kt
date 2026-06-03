package com.example.springflyway.application.port

import com.example.springflyway.adapter.out.persistence.entity.RestaurantEntity

interface RestaurantRepositoryPort {
    fun save(restaurant: RestaurantEntity): RestaurantEntity
    fun findById(id: Long): RestaurantEntity?
    fun findAll(): List<RestaurantEntity>
    fun update(restaurant: RestaurantEntity): RestaurantEntity
    fun deleteById(id: Long)
}