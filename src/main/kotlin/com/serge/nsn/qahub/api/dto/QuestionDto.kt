package com.serge.nsn.qahub.api.dto

import com.serge.nsn.qahub.data.entities.QuestionEntity

class QuestionDto(
    val id: Long? = null,
    val title: String = "",
    val content: String = "",
    val author: String = "",
    val tag: String,

) {
    constructor(questionEntity: QuestionEntity) : this(
        id = questionEntity.id,
        title = questionEntity.title,
        content = questionEntity.content,
        author = questionEntity.author,
        tag = questionEntity.tag

    )
}