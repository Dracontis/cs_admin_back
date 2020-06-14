package com.dracontis.cs.config.exception

interface ErrorCode {
    fun getCode(): String
}

enum class ErrorResponseCode(private val errorCode: String):
    ErrorCode {
    AUTHENTICATION_ERROR("authentication-error"),
    SYSTEM_ERROR("system-error");

    override fun getCode() = errorCode
}