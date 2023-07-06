package com.serge.nsn.qanda_hub.api.dto

import com.serge.nsn.qanda_hub.data.entities.UserEntity

class UserDto(
    val user_id: Long? = null,
    val name: String = "",
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val role: String = "",
    val questions: List<QuestionDto> = listOf()
) {
    constructor(userEntity: UserEntity) : this(
        user_id = userEntity.id,
        name = userEntity.name,
        username = userEntity.username,
        email = userEntity.email,
        role = userEntity.roles,
        questions = userEntity.questions.map { QuestionDto(it) }
    )
}