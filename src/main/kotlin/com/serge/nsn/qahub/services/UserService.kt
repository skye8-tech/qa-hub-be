package com.serge.nsn.qahub.services

import com.serge.nsn.qahub.api.dto.UserDto
import com.serge.nsn.qahub.data.entities.Subscription
import com.serge.nsn.qahub.data.entities.SubscriptionType
import com.serge.nsn.qahub.data.entities.UserEntity
import com.serge.nsn.qahub.data.repositories.SubscriptionRepository
import com.serge.nsn.qahub.data.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val subscriptionRepository: SubscriptionRepository
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
        userRepository.save(new_user)
        subscriptionRepository.save(
            Subscription(
                user = new_user,
                plan = SubscriptionType.FREE_TRIAL,
                startDate = LocalDate.now(),
                endDate = LocalDate.now().plusMonths(1)
            )
        )
        return UserDto(new_user)
    }

    fun getUserByUsername(username: String): Optional<UserEntity> {
        return userRepository.findByUsername(username)
    }

    fun getAll(): List<UserDto> {
        return userRepository.findAll().map { UserDto(it) }
    }

    fun getById(id: Long): UserDto {
        return UserDto(userRepository.findById(id).get())
    }

    fun batchRegister(newUsers: List<UserDto>): List<UserDto> {
        return userRepository.saveAll(newUsers.map
        { userDto ->
            UserEntity(
                id = userDto.user_id,
                name = userDto.name,
                username = userDto.username,
                email = userDto.email,
                password = BCryptPasswordEncoder().encode(userDto.password),
                roles = userDto.role

            )
        })
            .toList()
            .map { userEntity -> UserDto(userEntity) }
    }
}