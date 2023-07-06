package com.serge.nsn.qanda_hub.api.controller

import com.serge.nsn.qanda_hub.api.dto.UserDto
import com.serge.nsn.qanda_hub.configuration.jwt.JWTService
import com.serge.nsn.qanda_hub.configuration.jwt.LoginRequest
import com.serge.nsn.qanda_hub.services.UserService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
class AuthenticationController(
    private val jwtService: JWTService,
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody dto: UserDto): ResponseEntity<UserDto> =
        ResponseEntity(userService.register_user(dto), HttpStatus.CREATED)

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): String {
        val authentication = authenticationManager
            .authenticate(UsernamePasswordAuthenticationToken(request.userName, request.password))
        return if (authentication.isAuthenticated) {
            jwtService.generateToken(request.userName)
        } else {
            throw UsernameNotFoundException("Invalid user credentials")
        }
    }
}