package com.example.springflyway.application.service

import com.example.springflyway.adapter.out.persistence.entity.RestaurantEntity
import com.example.springflyway.application.exception.AlreadyExistsException
import com.example.springflyway.application.exception.NotFoundException
import com.example.springflyway.application.port.RestaurantRepositoryPort
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class RestaurantService(
    private val repository: RestaurantRepositoryPort
) {

    private val logger = KotlinLogging.logger {}

    fun create(restaurant: RestaurantEntity): RestaurantEntity {
        // Проверка на дубликат по имени
        if (repository.existsByName(restaurant.name)) {
            logger.warn { "Попытка создать ресторан с дублирующимся именем: ${restaurant.name}" }
            throw AlreadyExistsException("Ресторан с именем '${restaurant.name}' уже существует")
        }
        val saved = repository.save(restaurant)
        logger.info { "Создан ресторан: id=${saved.id}, name=${saved.name}" }
        return saved
    }

    fun findById(id: Long): RestaurantEntity {
        return repository.findById(id)
            ?: throw NotFoundException("Ресторан с id=$id не найден")
    }

    fun findAll(): List<RestaurantEntity> = repository.findAll()

    fun update(restaurant: RestaurantEntity): RestaurantEntity {
        // Проверяем, существует ли ресторан
        if (repository.findById(restaurant.id) == null) {
            throw NotFoundException("Ресторан с id=${restaurant.id} не найден")
        }
        val updated = repository.update(restaurant)
        logger.info { "Обновлён ресторан: id=${updated.id}, name=${updated.name}" }
        return updated
    }

    fun deleteById(id: Long) {
        if (repository.findById(id) == null) {
            throw NotFoundException("Ресторан с id=$id не найден")
        }
        repository.deleteById(id)
        logger.info { "Удалён ресторан с id=$id" }
    }
}