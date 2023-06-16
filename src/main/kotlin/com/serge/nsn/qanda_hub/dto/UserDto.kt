package com.serge.nsn.qanda_hub.dto

import com.serge.nsn.qanda_hub.entities.UserEntity

class UserDto (
        val user_id: Long?=null,
        val name: String = "",
        val username: String = "",
        val password: String = "",
        val email: String = "",
        val role: String = ""
        ){
        constructor(userEntity: UserEntity): this(
                user_id = userEntity.user_id,
                name = userEntity.name,
                username = userEntity.username,
                email = userEntity.email,
                role = userEntity.role
        )
}