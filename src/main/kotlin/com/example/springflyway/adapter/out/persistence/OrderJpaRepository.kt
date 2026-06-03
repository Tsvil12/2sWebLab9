package com.example.springflyway.adapter.out.persistence

import com.example.springflyway.adapter.out.persistence.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<OrderEntity, Long> {
    fun findByUserId(userId: Long): List<OrderEntity>
    fun findByStatus(status: String): List<OrderEntity>
}