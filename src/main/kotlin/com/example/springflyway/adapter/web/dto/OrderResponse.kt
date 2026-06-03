package com.example.springflyway.adapter.web.dto

import java.time.LocalDateTime

data class OrderResponse(
    val id: Long,
    val userId: Long,
    val status: String,
    val createdAt: LocalDateTime,
    val dishes: List<DishDto>
)