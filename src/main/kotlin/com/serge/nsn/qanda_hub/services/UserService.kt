package com.serge.nsn.qanda_hub.services

import com.serge.nsn.qanda_hub.api.dto.UserDto
import com.serge.nsn.qanda_hub.data.entities.UserEntity
import com.serge.nsn.qanda_hub.data.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun register_user(dto: UserDto): UserDto {
        val new_user = UserEntity(
            id = dto.user_id,
            name = dto.name,
            username = dto.username,
            email = dto.email,
            password = BCryptPasswordEncoder().encode(dto.password),
            roles = dto.role
        )
        return UserDto(userRepository.save(new_user))
    }

    fun getUserByUsername(username: String): Optional<UserEntity> {
        return userRepository.findByUsername(username)
    }

    fun getAll(): List<UserDto> {
        return userRepository.findAll().map{ UserDto(it) }
    }

    fun getById(id: Long): UserDto {
        return UserDto(userRepository.findById(id).get())
    }
}