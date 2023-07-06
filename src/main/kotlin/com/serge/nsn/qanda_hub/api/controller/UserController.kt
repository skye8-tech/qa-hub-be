package com.serge.nsn.qanda_hub.api.controller

import com.serge.nsn.qanda_hub.api.dto.UserDto
import com.serge.nsn.qanda_hub.data.entities.UserEntity
import com.serge.nsn.qanda_hub.services.UserService
import org.springframework.web.bind.annotation.*

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

}
