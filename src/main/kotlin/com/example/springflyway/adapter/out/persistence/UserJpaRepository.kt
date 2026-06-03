package com.example.springflyway.adapter.out.persistence

import com.example.springflyway.adapter.out.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<UserEntity, Long>