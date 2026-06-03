package com.example.springflyway.adapter.web

import com.example.springflyway.adapter.web.dto.UserDto
import com.example.springflyway.adapter.out.persistence.entity.UserEntity

object UserMapper {
    fun toEntity(dto: UserDto): UserEntity {
        return UserEntity(
            id = dto.id ?: 0,
            email = dto.email,
            firstName = dto.firstName,
            lastName = dto.lastName,
            isActive = dto.isActive
        )
    }

    fun toDto(entity: UserEntity): UserDto {
        return UserDto(
            id = entity.id,
            email = entity.email,
            firstName = entity.firstName,
            lastName = entity.lastName,
            isActive = entity.isActive
        )
    }
}