package com.example.springflyway.adapter.web

import com.example.springflyway.adapter.web.dto.AuthResponse
import com.example.springflyway.adapter.web.dto.LoginRequest
import com.example.springflyway.adapter.web.dto.RegisterRequest
import com.example.springflyway.application.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Регистрация и вход в систему")
open class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Пользователь создан, JWT токен возвращён"),
            ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            ApiResponse(responseCode = "409", description = "Пользователь с таким email уже существует")
        ]
    )
    fun register(@Valid @RequestBody request: RegisterRequest): ResponseEntity<AuthResponse> {
        val response = authService.register(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PostMapping("/login")
    @Operation(summary = "Вход в систему")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Успешный вход, JWT токен возвращён"),
            ApiResponse(responseCode = "401", description = "Неверный email или пароль")
        ]
    )
    fun login(@Valid @RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        val response = authService.login(request)
        return ResponseEntity.ok(response)
    }
}