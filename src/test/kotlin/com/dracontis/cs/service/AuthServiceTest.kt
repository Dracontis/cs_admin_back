package com.dracontis.cs.service

import com.dracontis.cs.ADMIN_LOGIN
import com.dracontis.cs.ADMIN_PASS
import com.dracontis.cs.ApplicationTest
import com.dracontis.cs.controller.entity.AuthRequest
import com.dracontis.cs.controller.entity.AuthResponse
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.returnResult
import reactor.core.publisher.Mono

internal class AuthServiceTest : ApplicationTest() {

    @Test
    fun `Login with fake credentials and expect error`() {
    }

    @Test
    fun `Login to administration panel`() {
        val request = AuthRequest(
            username = ADMIN_LOGIN,
            password = ADMIN_PASS
        )
        val response = webClient.post().uri("/login")
            .body(Mono.just(request), AuthRequest::class.java)
            .exchange()
            .expectStatus().isOk
            .expectBody(AuthResponse::class.java)
            .returnResult()
        assertThat(response.responseBody?.token, notNullValue())
    }

    @Test
    fun `Expand token lifetime`() {

    }

    @Test
    fun `Change default password`() {

    }

    @Test
    fun `Create new user`() {

    }

    @Test
    fun `Try to create new user with staff role and expect failure`() {

    }
}