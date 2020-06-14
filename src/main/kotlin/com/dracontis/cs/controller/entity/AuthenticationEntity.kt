package com.dracontis.cs.controller.entity

import com.dracontis.cs.repository.Role

data class AuthRequest(
    val username: String,
    val password: String
): Request

data class AuthResponse(
    val token: String
): Response

data class DeleteRequest(
    val id: Long
): Request

data class RegisterRequest(
    val username: String,
    val password: String,
    val role: Role
): Request