package com.dracontis.cs.config.security

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.lang.Exception

@Component
class AuthenticationManager : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val token = authentication.credentials.toString()
        val username = try {
            getUsernameFromToken(token)
        } catch (e: Exception) {
            return Mono.empty()
        }

        if (isTokenExpired(token)) {
            return Mono.empty()
        }

        val claims = getAllClaimsFromToken(token)
        val authorities = claims.get(AUTHORITIES_KEY, List::class.java)
            .map { role -> SimpleGrantedAuthority(role as String) }
        val auth = UsernamePasswordAuthenticationToken(username, username, authorities)
        SecurityContextHolder.getContext().authentication = auth

        return Mono.just(auth)
    }
}