package com.example.springflyway.adapter.out.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val email: String,
    val firstName: String,
    val lastName: String,
    val isActive: Boolean = true
)