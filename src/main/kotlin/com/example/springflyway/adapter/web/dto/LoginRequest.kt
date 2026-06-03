package com.example.springflyway.adapter.web.dto

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank(message = "Email обязателен")
    val email: String,

    @field:NotBlank(message = "Пароль обязателен")
    val password: String
)