package com.example.springflyway.adapter.web.dto

import java.math.BigDecimal

data class DishDto(
    val id: Long? = null,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val isAvailable: Boolean = true,
    val restaurantId: Long
)