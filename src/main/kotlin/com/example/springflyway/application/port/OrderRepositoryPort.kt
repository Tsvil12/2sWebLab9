package com.example.springflyway.application.port

import com.example.springflyway.adapter.out.persistence.entity.OrderEntity

interface OrderRepositoryPort {
    fun save(order: OrderEntity): OrderEntity
    fun findById(id: Long): OrderEntity?
    fun findAll(): List<OrderEntity>
    fun findByUserId(userId: Long): List<OrderEntity>
    fun findByStatus(status: String): List<OrderEntity>
    fun update(order: OrderEntity): OrderEntity
    fun deleteById(id: Long)
}