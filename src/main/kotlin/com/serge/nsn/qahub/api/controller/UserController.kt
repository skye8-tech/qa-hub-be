package com.serge.nsn.qahub.api.controller

import com.serge.nsn.qahub.api.dto.UserDto
import com.serge.nsn.qahub.services.UserService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@CrossOrigin
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
) {

    @GetMapping("/all")
    fun getUsers(): List<UserDto> = userService.getAll()

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Long) = userService.getById(userId)

    @GetMapping("/user")
    fun getCurrentUser(principal: Principal): String {
        val username = principal.name
        return "Current user: $username"
    }

}
