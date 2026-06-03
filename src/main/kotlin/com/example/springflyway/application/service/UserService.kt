package com.example.springflyway.application.service

import com.example.springflyway.adapter.out.persistence.entity.UserEntity
import com.example.springflyway.application.exception.AlreadyExistsException
import com.example.springflyway.application.exception.NotFoundException
import com.example.springflyway.application.port.UserRepositoryPort
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepositoryPort
) {

    private val logger = KotlinLogging.logger {}

    fun createUser(user: UserEntity): UserEntity {
        if (userRepository.existsByEmail(user.email)) {
            logger.warn { "Попытка создать пользователя с существующим email: ${user.email}" }
            throw AlreadyExistsException("Пользователь с email '${user.email}' уже существует")
        }
        val saved = userRepository.save(user)
        logger.info { "Создан пользователь: id=${saved.id}, email=${saved.email}" }
        return saved
    }

    fun findById(id: Long): UserEntity {
        return userRepository.findById(id)
            ?: throw NotFoundException("Пользователь с id=$id не найден")
    }

    fun findAll(): List<UserEntity> = userRepository.findAll()

    fun deleteById(id: Long) {
        if (userRepository.findById(id) == null) {
            throw NotFoundException("Пользователь с id=$id не найден")
        }
        userRepository.deleteById(id)
        logger.info { "Удалён пользователь с id=$id" }
    }
    fun findByEmail(email: String): UserEntity? = userRepository.findByEmail(email)
}