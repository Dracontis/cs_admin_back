package com.dracontis.cs.config.exception

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.ResourceBundleMessageSource

abstract class ApplicationException(private val errorCode: ErrorCode) : Exception() {
    private val messageSource = ResourceBundleMessageSource().apply {
        setBasename("messages")
        setDefaultEncoding(Charsets.UTF_8.displayName())
    }

    override fun getLocalizedMessage(): String {
        return messageSource.getMessage(errorCode.getCode(), arrayOf(),
            LocaleContextHolder.getLocale()
        )
    }
}