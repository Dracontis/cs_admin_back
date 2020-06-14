package com.dracontis.cs.repository

import org.springframework.data.annotation.ReadOnlyProperty
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

enum class Role {
    ADMINISTRATOR,
    MANAGER,
    STAFF
}

@Entity
@Table(name = "USER", schema = "CS")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val username: String,
    val password: String,
    @Enumerated(EnumType.STRING)
    val role: Role,
    @ReadOnlyProperty
    val created: LocalDateTime = LocalDateTime.now(),
    @ReadOnlyProperty
    val lastmodified: LocalDateTime = LocalDateTime.now()
)