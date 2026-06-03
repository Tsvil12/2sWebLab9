package com.example.springflyway.application.service

import com.example.springflyway.adapter.out.persistence.entity.OrderEntity
import com.example.springflyway.application.port.OrderRepositoryPort
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val repository: OrderRepositoryPort
) {
    fun create(order: OrderEntity): OrderEntity = repository.save(order)
    fun findById(id: Long): OrderEntity? = repository.findById(id)
    fun findAll(userId: Long?, status: String?): List<OrderEntity> {
        return when {
            userId != null -> repository.findByUserId(userId)
            status != null -> repository.findByStatus(status)
            else -> repository.findAll()
        }
    }
    fun updateStatus(order: OrderEntity): OrderEntity = repository.update(order)
}