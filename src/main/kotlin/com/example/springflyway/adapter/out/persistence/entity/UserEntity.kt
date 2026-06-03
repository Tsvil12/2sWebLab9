package com.example.springflyway.adapter.out.persistence.entity

import com.example.springflyway.domain.Role
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
    val isActive: Boolean = true,
    @Column(nullable = false)
    val password: String = "",
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role = Role.USER
)