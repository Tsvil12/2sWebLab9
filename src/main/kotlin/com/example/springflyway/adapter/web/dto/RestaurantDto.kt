package com.example.springflyway.adapter.web.dto

import jakarta.validation.constraints.NotBlank

data class RestaurantDto(
    val id: Long? = null,
    @field:NotBlank(message = "Название не может быть пустым")
    val name: String,
    @field:NotBlank(message = "Адрес не может быть пустым")
    val address: String
)