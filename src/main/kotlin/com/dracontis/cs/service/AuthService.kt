package com.dracontis.cs.service

import com.dracontis.cs.config.exception.ErrorResponseCode
import com.dracontis.cs.config.exception.AuthenicationException
import com.dracontis.cs.repository.Role
import com.dracontis.cs.repository.User
import com.dracontis.cs.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
){

    fun login(username: String, password: String): User {
        val user = userRepository.findByUsername(username) ?: throw AuthenicationException(ErrorResponseCode.AUTHENTICATION_ERROR)

        if (passwordEncoder.matches(password, user.password)) {
            return user
        }

        throw AuthenicationException(ErrorResponseCode.AUTHENTICATION_ERROR)
    }

    fun register(username: String, password: String, role: Role) = userRepository.save(
        User(
            username = username,
            password = passwordEncoder.encode(password),
            role = role
        )
    )

    fun deleteUser(id: Long) = userRepository.deleteById(id)

    fun updateUser(user: User) = userRepository.save(user)
}