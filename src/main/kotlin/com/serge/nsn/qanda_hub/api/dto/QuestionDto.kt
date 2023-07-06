package com.serge.nsn.qanda_hub.api.dto

import com.serge.nsn.qanda_hub.data.entities.QuestionEntity

class QuestionDto(
    val question_id: Long? = null,
    val title: String = "",
    val content: String = "",
//    val user: UserDto? = null
) {
    constructor(questionEntity: QuestionEntity) : this(
        question_id = questionEntity.id,
        title = questionEntity.title,
        content = questionEntity.content,
//        user = UserDto(questionEntity.user)
    )
}