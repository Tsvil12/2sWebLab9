package com.example.springflyway.adapter.web

import com.example.springflyway.adapter.web.dto.DishDto
import com.example.springflyway.adapter.out.persistence.entity.DishEntity
import com.example.springflyway.adapter.out.persistence.entity.RestaurantEntity

object DishMapper {
    fun toEntity(dto: DishDto, restaurant: RestaurantEntity): DishEntity {
        return DishEntity(
            id = dto.id ?: 0,
            name = dto.name,
            description = dto.description,
            price = dto.price,
            isAvailable = dto.isAvailable,
            restaurant = restaurant
        )
    }

    fun toDto(entity: DishEntity): DishDto {
        return DishDto(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            price = entity.price,
            isAvailable = entity.isAvailable,
            restaurantId = entity.restaurant?.id ?: 0
        )
    }
}