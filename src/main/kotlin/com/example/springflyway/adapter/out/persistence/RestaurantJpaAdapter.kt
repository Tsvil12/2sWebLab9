package com.example.springflyway.adapter.out.persistence

import com.example.springflyway.application.port.RestaurantRepositoryPort
import com.example.springflyway.adapter.out.persistence.entity.RestaurantEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
open class RestaurantJpaAdapter(
    private val jpaRepository: RestaurantJpaRepository
) : RestaurantRepositoryPort {
    override fun save(restaurant: RestaurantEntity): RestaurantEntity = jpaRepository.save(restaurant)
    override fun findById(id: Long): RestaurantEntity? = jpaRepository.findById(id).orElse(null)
    override fun findAll(): List<RestaurantEntity> = jpaRepository.findAll()
    override fun update(restaurant: RestaurantEntity): RestaurantEntity = jpaRepository.save(restaurant)
    override fun deleteById(id: Long) = jpaRepository.deleteById(id)
}