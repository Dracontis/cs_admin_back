package com.dracontis.cs.config.security

import com.dracontis.cs.repository.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

private const val SIGNING_KEY = "devglan123r"
const val AUTHORITIES_KEY = "scopes"

fun getUsernameFromToken(token: String) =
    getAllClaimsFromToken(token).subject

fun getExpirationDateFromToken(token: String) =
    getAllClaimsFromToken(token).expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

fun getAllClaimsFromToken(token: String) = Jwts.parser()
    .setSigningKey(SIGNING_KEY)
    .parseClaimsJws(token)
    .body

fun isTokenExpired(token: String) =
    getExpirationDateFromToken(token).isBefore(LocalDateTime.now(ZoneId.systemDefault()))

fun User.generateToken(): String {
    val authorities = listOf(this.role)
    return Jwts.builder()
        .setSubject(this.username)
        .claim(AUTHORITIES_KEY, authorities)
        .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
        .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
        .setExpiration(Date.from(LocalDateTime.now().plusHours(1L).atZone(ZoneId.systemDefault()).toInstant()))
        .compact()

}