package com.example.springflyway.adapter.out.persistence

import com.example.springflyway.application.port.UserRepositoryPort
import com.example.springflyway.adapter.out.persistence.entity.UserEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
open class UserJpaAdapter(
    private val jpaRepository: UserJpaRepository
) : UserRepositoryPort {
    override fun save(user: UserEntity): UserEntity = jpaRepository.save(user)
    override fun findById(id: Long): UserEntity? = jpaRepository.findById(id).orElse(null)
    override fun findAll(): List<UserEntity> = jpaRepository.findAll()
    override fun update(user: UserEntity): UserEntity = jpaRepository.save(user)
    override fun deleteById(id: Long) = jpaRepository.deleteById(id)
    override fun existsByEmail(email: String): Boolean = jpaRepository.existsByEmail(email)
    override fun findByEmail(email: String): UserEntity? = jpaRepository.findByEmail(email)
}