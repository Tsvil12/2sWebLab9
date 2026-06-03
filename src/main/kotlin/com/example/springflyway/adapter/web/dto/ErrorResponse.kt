package com.example.springflyway.adapter.web.dto

import java.time.LocalDateTime

open class ErrorResponse(
    val status: Int,
    val message: String? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
)

class ValidationErrorResponse(
    status: Int,
    message: String? = null,
    val errors: Map<String, String>,
    timestamp: LocalDateTime = LocalDateTime.now()
) : ErrorResponse(status, message, timestamp)