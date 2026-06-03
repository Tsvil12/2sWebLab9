package com.example.springflyway.adapter.out.persistence

import com.example.springflyway.application.port.DishRepositoryPort
import com.example.springflyway.adapter.out.persistence.entity.DishEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class DishJpaAdapter(
    private val jpaRepository: DishJpaRepository
) : DishRepositoryPort {
    override fun save(dish: DishEntity): DishEntity = jpaRepository.save(dish)
    override fun findById(id: Long): DishEntity? = jpaRepository.findById(id).orElse(null)
    override fun findAll(): List<DishEntity> = jpaRepository.findAll()
    override fun findByNameContaining(namePart: String): List<DishEntity> = jpaRepository.findByNameContaining(namePart)
    override fun findByRestaurantId(restaurantId: Long): List<DishEntity> = jpaRepository.findByRestaurantId(restaurantId)
    override fun update(dish: DishEntity): DishEntity = jpaRepository.save(dish)
    override fun deleteById(id: Long) = jpaRepository.deleteById(id)
}