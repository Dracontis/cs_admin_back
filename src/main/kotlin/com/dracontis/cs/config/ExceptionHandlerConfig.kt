package com.dracontis.cs.config

import com.dracontis.cs.config.exception.AuthenicationException
import com.dracontis.cs.config.exception.ProcessingException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus


data class ErrorResponse(
    val message: String
)

@ControllerAdvice
class ExceptionHandlerConfig {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler
    fun handleProcessingException(e: ProcessingException) = ErrorResponse(e.localizedMessage)

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler
    fun handleAuthorizationException(e: AuthenicationException) = ErrorResponse(e.localizedMessage)
}