package com.example.springflyway.adapter.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterRequest(
    @field:Email(message = "Некорректный формат email")
    @field:NotBlank(message = "Email обязателен")
    val email: String,

    @field:NotBlank(message = "Пароль обязателен")
    @field:Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    val password: String,

    @field:NotBlank(message = "Имя обязательно")
    val firstName: String,

    @field:NotBlank(message = "Фамилия обязательна")
    val lastName: String
)