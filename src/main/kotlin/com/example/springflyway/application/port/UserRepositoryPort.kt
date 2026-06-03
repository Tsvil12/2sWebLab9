package com.example.springflyway.application.port

import com.example.springflyway.adapter.out.persistence.entity.UserEntity

interface UserRepositoryPort {
    fun save(user: UserEntity): UserEntity
    fun findById(id: Long): UserEntity?
    fun findAll(): List<UserEntity>
    fun update(user: UserEntity): UserEntity
    fun deleteById(id: Long)
    fun existsByEmail(email: String): Boolean
}