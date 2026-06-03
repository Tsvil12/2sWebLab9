package com.example.springflyway.adapter.web.dto

data class UserDto(
    val id: Long? = null,
    val email: String,
    val firstName: String,
    val lastName: String,
    val isActive: Boolean = true
)