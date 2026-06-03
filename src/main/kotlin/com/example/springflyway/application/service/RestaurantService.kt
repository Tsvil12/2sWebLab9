package com.example.springflyway.application.service

import com.example.springflyway.adapter.out.persistence.entity.RestaurantEntity
import com.example.springflyway.application.exception.AlreadyExistsException
import com.example.springflyway.application.exception.NotFoundException
import com.example.springflyway.application.port.RestaurantRepositoryPort
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
open class RestaurantService(
    private val repository: RestaurantRepositoryPort
) {

    private val logger = KotlinLogging.logger {}

    @Cacheable(cacheNames = ["restaurants"])
    fun findAll(): List<RestaurantEntity> {
        logger.info { "Загрузка всех ресторанов из БД" }
        return repository.findAll()
    }

    @Cacheable(cacheNames = ["restaurants"], key = "#id")
    fun findById(id: Long): RestaurantEntity {
        logger.info { "Загрузка ресторана id=$id из БД" }
        return repository.findById(id)
            ?: throw NotFoundException("Ресторан с id=$id не найден")
    }

    fun create(restaurant: RestaurantEntity): RestaurantEntity {
        if (repository.existsByName(restaurant.name)) {
            logger.warn { "Попытка создать ресторан с дублирующимся именем: ${restaurant.name}" }
            throw AlreadyExistsException("Ресторан с именем '${restaurant.name}' уже существует")
        }
        val saved = repository.save(restaurant)
        logger.info { "Создан ресторан: id=${saved.id}, name=${saved.name}" }
        return saved
    }

    fun update(restaurant: RestaurantEntity): RestaurantEntity {
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