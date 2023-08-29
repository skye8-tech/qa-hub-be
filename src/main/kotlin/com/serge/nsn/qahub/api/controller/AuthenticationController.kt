package com.serge.nsn.qahub.api.controller

import com.serge.nsn.qahub.api.dto.UserDto
import com.serge.nsn.qahub.api.model.LoginRequest
import com.serge.nsn.qahub.api.model.LoginResponse
import com.serge.nsn.qahub.configuration.jwt.JWTService
import com.serge.nsn.qahub.services.UserService
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
class jAuthenticationController(
    private val jwtService: JWTService,
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody dto: UserDto): ResponseEntity<UserDto> =
        ResponseEntity(userService.register_user(dto), HttpStatus.CREATED)

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        val authentication = authenticationManager
            .authenticate(UsernamePasswordAuthenticationToken(request.username, request.password))
        return if (authentication.isAuthenticated) {
            LoginResponse(accessToken = jwtService.generateToken(request.username))
        } else {
            throw UsernameNotFoundException("Invalid user credentials")
        }
    }

    @RequestMapping("/batch-registration")


    @PostMapping
    fun createUsers(@RequestBody newUsers: List<UserDto>): ResponseEntity<List<UserDto>> {
        return ResponseEntity(userService.batchRegister(newUsers), HttpStatus.CREATED)

    }
}