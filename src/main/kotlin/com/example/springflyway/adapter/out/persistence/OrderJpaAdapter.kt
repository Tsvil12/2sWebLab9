package com.example.springflyway.adapter.out.persistence

import com.example.springflyway.application.port.OrderRepositoryPort
import com.example.springflyway.adapter.out.persistence.entity.OrderEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
open class OrderJpaAdapter(
    private val jpaRepository: OrderJpaRepository
) : OrderRepositoryPort {
    override fun save(order: OrderEntity): OrderEntity = jpaRepository.save(order)
    override fun findById(id: Long): OrderEntity? = jpaRepository.findById(id).orElse(null)
    override fun findAll(): List<OrderEntity> = jpaRepository.findAll()
    override fun findByUserId(userId: Long): List<OrderEntity> = jpaRepository.findByUserId(userId)
    override fun findByStatus(status: String): List<OrderEntity> = jpaRepository.findByStatus(status)
    override fun update(order: OrderEntity): OrderEntity = jpaRepository.save(order)
    override fun deleteById(id: Long) = jpaRepository.deleteById(id)
}