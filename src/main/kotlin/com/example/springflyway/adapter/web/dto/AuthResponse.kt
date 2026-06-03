package com.example.springflyway.adapter.web.dto

data class AuthResponse(
    val token: String,
    val email: String,
    val role: String
)