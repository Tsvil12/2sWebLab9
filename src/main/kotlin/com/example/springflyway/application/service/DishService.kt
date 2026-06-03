package com.example.springflyway.application.service

import com.example.springflyway.adapter.out.persistence.entity.DishEntity
import com.example.springflyway.application.port.DishRepositoryPort
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
open class DishService(
    private val repository: DishRepositoryPort
) {

    private val logger = KotlinLogging.logger {}

    @Cacheable(cacheNames = ["dishes"], key = "#restaurantId")
    fun findByRestaurantId(restaurantId: Long): List<DishEntity> {
        logger.info { "Загрузка блюд для ресторана id=$restaurantId из БД" }
        return repository.findByRestaurantId(restaurantId)
    }

    @Cacheable(cacheNames = ["dishes"], key = "#id")
    fun findById(id: Long): DishEntity? {
        logger.info { "Загрузка блюда id=$id из БД" }
        return repository.findById(id)
    }

    @CacheEvict(cacheNames = ["dishes"], allEntries = true)
    fun create(dish: DishEntity): DishEntity = repository.save(dish)

    fun findAll(namePart: String?): List<DishEntity> = repository.findAll()

    @CacheEvict(cacheNames = ["dishes"], allEntries = true)
    fun update(dish: DishEntity): DishEntity = repository.update(dish)

    @CacheEvict(cacheNames = ["dishes"], allEntries = true)
    fun deleteById(id: Long) = repository.deleteById(id)
}