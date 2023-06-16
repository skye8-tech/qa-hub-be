package com.serge.nsn.qanda_hub.dto

import com.serge.nsn.qanda_hub.entities.AnswerEntity

class AnswerDto(
    val answer_id: Long? = null,
    val content: String = "",
) {
    constructor(answerEntity: AnswerEntity): this(
        answer_id = answerEntity.answer_id,
        content = answerEntity.content
    )
}