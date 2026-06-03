package com.example.springflyway.adapter.out.persistence

import com.example.springflyway.adapter.out.persistence.entity.RestaurantEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RestaurantJpaRepository : JpaRepository<RestaurantEntity, Long>