package com.serge.nsn.qanda_hub.dto

import com.serge.nsn.qanda_hub.entities.QuestionEntity

class QuestionDto(
    val question_id:Long? = null,
    val title: String = "",
    val content: String = "",
) {
   constructor(questionEntity: QuestionEntity): this(
       question_id = questionEntity.question_id,
       title = questionEntity.title,
       content = questionEntity.content
   )
}