package com.example.springflyway.adapter.web.dto

data class OrderCreateRequest(
    val userId: Long,
    val dishIds: List<Long>
)