package com.example.springflyway.adapter.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserDto(
    val id: Long? = null,
    @field:NotBlank(message = "Email не может быть пустым")
    @field:Email(message = "Некорректный формат email")
    val email: String,
    @field:NotBlank(message = "Имя не может быть пустым")
    val firstName: String,
    @field:NotBlank(message = "Фамилия не может быть пустой")
    val lastName: String,
    val isActive: Boolean = true
)