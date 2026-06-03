package com.example.springflyway.adapter.out.persistence

import com.example.springflyway.adapter.out.persistence.entity.DishEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DishJpaRepository : JpaRepository<DishEntity, Long> {
    @EntityGraph(attributePaths = ["restaurant"])
    override fun findById(id: Long): java.util.Optional<DishEntity>

    @EntityGraph(attributePaths = ["restaurant"])
    fun findByRestaurantId(restaurantId: Long): List<DishEntity>

    @Query("SELECT d FROM DishEntity d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    fun findByNameContaining(@Param("namePart") namePart: String): List<DishEntity>
}