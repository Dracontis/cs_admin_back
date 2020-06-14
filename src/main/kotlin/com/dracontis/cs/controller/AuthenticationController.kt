package com.dracontis.cs.controller

import com.dracontis.cs.config.security.generateToken
import com.dracontis.cs.controller.entity.AuthRequest
import com.dracontis.cs.controller.entity.AuthResponse
import com.dracontis.cs.controller.entity.DeleteRequest
import com.dracontis.cs.controller.entity.RegisterRequest
import com.dracontis.cs.repository.User
import com.dracontis.cs.repository.UserRepository
import com.dracontis.cs.repository.Role
import com.dracontis.cs.service.AuthService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class AuthenticationController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): Mono<Void> = Mono.just(authService.register(
        username = request.username,
        password = request.password,
        role = request.role
    )).then()

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): Mono<AuthResponse> {
        val user = authService.login(
            username = request.username,
            password = request.password
        )
        return Mono.just(AuthResponse(user.generateToken()))
    }

    @PostMapping("/delete")
    fun delete(@RequestBody request: DeleteRequest): Mono<Void> =
        Mono.just(authService.deleteUser(request.id)).then()

    @GetMapping("/hello")
    fun test(authentication: Authentication) = Mono.just("Hello")
}