package com.example.springflyway.application.service

import com.example.springflyway.adapter.out.persistence.entity.UserEntity
import com.example.springflyway.application.port.UserRepositoryPort
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepositoryPort
) {
    fun createUser(user: UserEntity): UserEntity = userRepository.save(user)
    fun findById(id: Long): UserEntity? = userRepository.findById(id)
    fun findAll(): List<UserEntity> = userRepository.findAll()
    fun deleteById(id: Long) = userRepository.deleteById(id)
}