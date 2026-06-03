package com.example.springflyway.adapter.web

import com.example.springflyway.adapter.web.dto.RestaurantDto
import com.example.springflyway.adapter.out.persistence.entity.RestaurantEntity

object RestaurantMapper {
    fun toEntity(dto: RestaurantDto): RestaurantEntity {
        return RestaurantEntity(
            id = dto.id ?: 0,
            name = dto.name,
            address = dto.address
        )
    }

    fun toDto(entity: RestaurantEntity): RestaurantDto {
        return RestaurantDto(
            id = entity.id,
            name = entity.name,
            address = entity.address
        )
    }
}