package com.example.springflyway.adapter.web

import com.example.springflyway.adapter.web.dto.UserDto
import com.example.springflyway.application.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping
    fun createUser(@RequestBody dto: UserDto): ResponseEntity<UserDto> {
        val user = UserMapper.toEntity(dto)
        val saved = userService.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(saved))
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserDto> {
        val user = userService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(UserMapper.toDto(user))
    }
}