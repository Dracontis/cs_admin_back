package com.dracontis.cs.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @Value("\${some.prop}")
    lateinit var prop: String

    @GetMapping
    fun hello() = "$prop, world!"
}