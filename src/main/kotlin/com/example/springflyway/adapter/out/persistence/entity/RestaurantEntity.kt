package com.example.springflyway.adapter.out.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "restaurants")
class RestaurantEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String = "",
    val address: String = ""
) {
    constructor() : this(0, "", "")
}