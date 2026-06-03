package com.example.springflyway.adapter.web

import com.example.springflyway.adapter.web.dto.ErrorResponse
import com.example.springflyway.adapter.web.dto.ValidationErrorResponse
import com.example.springflyway.application.exception.AlreadyExistsException
import com.example.springflyway.application.exception.InvalidOrderStateException
import com.example.springflyway.application.exception.NotFoundException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(e: NotFoundException): ResponseEntity<ErrorResponse> {
        logger.warn { e.message }
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(HttpStatus.NOT_FOUND.value(), e.message))
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun handleAlreadyExists(e: AlreadyExistsException): ResponseEntity<ErrorResponse> {
        logger.warn { e.message }
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse(HttpStatus.CONFLICT.value(), e.message))
    }

    @ExceptionHandler(InvalidOrderStateException::class)
    fun handleInvalidOrderState(e: InvalidOrderStateException): ResponseEntity<ErrorResponse> {
        logger.warn { e.message }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(e: MethodArgumentNotValidException): ResponseEntity<ValidationErrorResponse> {
        val errors = e.bindingResult.allErrors.associate {
            when (it) {
                is FieldError -> it.field to (it.defaultMessage ?: "Incorrect value")
                else -> it.objectName to (it.defaultMessage ?: "Incorrect value")
            }
        }
        logger.warn { "Validation error: $errors" }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                errors
            ))
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentials(e: BadCredentialsException): ResponseEntity<ErrorResponse> {
        logger.warn { "Ошибка аутентификации: ${e.message}" }
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Неверный email или пароль"))
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDenied(e: AccessDeniedException): ResponseEntity<ErrorResponse> {
        logger.warn { "Доступ запрещён: ${e.message}" }
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(ErrorResponse(HttpStatus.FORBIDDEN.value(), "Доступ запрещён"))
    }

    @ExceptionHandler(Exception::class)
    fun handleUnexpected(e: Exception): ResponseEntity<ErrorResponse> {
        logger.error(e) { "Unexpected error" }
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"))
    }
}