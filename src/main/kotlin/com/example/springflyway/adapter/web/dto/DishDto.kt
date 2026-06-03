package com.example.springflyway.adapter.web.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class DishDto(
    val id: Long? = null,
    @field:NotBlank(message = "Название не может быть пустым")
    val name: String,
    @field:NotBlank(message = "Описание не может быть пустым")
    val description: String,
    @field:Positive(message = "Цена должна быть больше 0")
    val price: BigDecimal,
    val isAvailable: Boolean = true,
    val restaurantId: Long
)