package com.serge.nsn.qanda_hub.services

import com.serge.nsn.qanda_hub.dto.UserDto
import com.serge.nsn.qanda_hub.entities.UserEntity
import com.serge.nsn.qanda_hub.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun register_user(dto: UserDto): UserDto{
        val new_user = UserEntity(
            user_id = dto.user_id,
            name = dto.name,
            username = dto.username,
            email = dto.email,
            password = BCryptPasswordEncoder().encode(dto.password),
            role = dto.role
        )
        return UserDto(userRepository.save(new_user))
    }

    fun getUserByUsername(username: String): UserEntity?{
        return userRepository.findByUsername(username)
    }

    fun getAll(): List<UserEntity> {
        return userRepository.findAll()
    }


}