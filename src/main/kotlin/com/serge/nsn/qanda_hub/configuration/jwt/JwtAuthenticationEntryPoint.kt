package com.serge.nsn.qanda_hub.configuration.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        logger.error(authException?.message)
        response?.let {
            it.contentType = MediaType.APPLICATION_JSON_VALUE
            it.status = HttpServletResponse.SC_UNAUTHORIZED
            val body = mutableMapOf<String, Any>(
                "status" to HttpServletResponse.SC_UNAUTHORIZED,
                "error" to "Unauthorized",
                "message" to (authException?.message ?: ""),
                "path" to (request?.servletPath ?: "")
            )
            ObjectMapper().writeValue(
                it.outputStream, body
            )
        }
    }

}