package com.example.springflyway.application.service

import com.example.springflyway.adapter.web.dto.AuthResponse
import com.example.springflyway.adapter.web.dto.LoginRequest
import com.example.springflyway.adapter.web.dto.RegisterRequest
import com.example.springflyway.adapter.out.persistence.entity.UserEntity
import com.example.springflyway.application.exception.AlreadyExistsException
import com.example.springflyway.application.exception.NotFoundException
import com.example.springflyway.application.port.UserRepositoryPort
import com.example.springflyway.domain.Role
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepositoryPort,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {
    private val logger = KotlinLogging.logger {}

    fun register(request: RegisterRequest): AuthResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw AlreadyExistsException("Пользователь с email ${request.email} уже существует")
        }

        val user = UserEntity(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            firstName = request.firstName,
            lastName = request.lastName,
            role = Role.USER,
            isActive = true
        )

        val savedUser = userRepository.save(user)
        logger.info { "Зарегистрирован пользователь: ${savedUser.email}" }

        val token = jwtService.generateToken(savedUser.email, savedUser.role.name)
        return AuthResponse(token, savedUser.email, savedUser.role.name)
    }

    fun login(request: LoginRequest): AuthResponse {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(request.email, request.password)
            )
        } catch (e: AuthenticationException) {
            logger.warn { "Ошибка входа для ${request.email}: ${e.message}" }
            throw e
        }

        val user = userRepository.findByEmail(request.email)
            ?: throw NotFoundException("Пользователь не найден")

        logger.info { "Вход пользователя: ${user.email}" }

        val token = jwtService.generateToken(user.email, user.role.name)
        return AuthResponse(token, user.email, user.role.name)
    }
}