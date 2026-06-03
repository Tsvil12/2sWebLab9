package com.example.springflyway.adapter.web

import com.example.springflyway.adapter.web.dto.*
import com.example.springflyway.adapter.out.persistence.entity.OrderEntity
import com.example.springflyway.adapter.out.persistence.entity.OrderStatus
import com.example.springflyway.application.service.DishService
import com.example.springflyway.application.service.OrderService
import com.example.springflyway.application.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    private val orderService: OrderService,
    private val userService: UserService,
    private val dishService: DishService
) {
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun createOrder(
        @Valid @RequestBody request: OrderCreateRequest,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<OrderResponse> {
        val user = userService.findByEmail(userDetails.username)
            ?: return ResponseEntity.badRequest().build()

        val dishes = request.dishIds.mapNotNull { dishService.findById(it) }
        if (dishes.size != request.dishIds.size) {
            return ResponseEntity.badRequest().build()
        }

        val order = OrderEntity(
            status = OrderStatus.PENDING,
            createdAt = LocalDateTime.now(),
            user = user,
            dishes = dishes.toMutableSet()
        )

        val saved = orderService.create(order)
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved))
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @orderController.isOwner(authentication, #id)")
    fun getOrderById(@PathVariable id: Long): ResponseEntity<OrderResponse> {
        val order = orderService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(toResponse(order))
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun getOrders(
        @RequestParam(required = false) userId: Long?,
        @RequestParam(required = false) status: String?
    ): List<OrderResponse> {
        return orderService.findAll(userId, status).map { toResponse(it) }
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    fun updateOrderStatus(
        @PathVariable id: Long,
        @RequestBody request: OrderStatusUpdateRequest
    ): ResponseEntity<OrderResponse> {
        val order = orderService.findById(id) ?: return ResponseEntity.notFound().build()

        val newStatus = try {
            OrderStatus.valueOf(request.status.uppercase())
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().build()
        }

        val updatedOrder = order.copy(status = newStatus)
        val saved = orderService.updateStatus(updatedOrder)
        return ResponseEntity.ok(toResponse(saved))
    }

    private fun toResponse(order: OrderEntity): OrderResponse {
        return OrderResponse(
            id = order.id,
            userId = order.user?.id ?: 0,
            status = order.status.name,
            createdAt = order.createdAt,
            dishes = order.dishes.map { DishMapper.toDto(it) }
        )
    }

    // Метод для проверки владельца заказа (используется в @PreAuthorize)
    fun isOwner(authentication: org.springframework.security.core.Authentication, orderId: Long): Boolean {
        val email = authentication.name
        val order = orderService.findById(orderId) ?: return false
        val user = userService.findByEmail(email) ?: return false
        return order.user?.id == user.id
    }
}