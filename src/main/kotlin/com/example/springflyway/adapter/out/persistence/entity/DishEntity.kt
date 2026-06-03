package com.example.springflyway.adapter.out.persistence.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "dishes")
class DishEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val price: BigDecimal = BigDecimal.ZERO,
    val isAvailable: Boolean = true,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    val restaurant: RestaurantEntity? = null
) {
    // Для Hibernate
    constructor() : this(0, "", "", BigDecimal.ZERO, true, null)
}