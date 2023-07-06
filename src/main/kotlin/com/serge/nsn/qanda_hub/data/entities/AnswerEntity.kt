package com.serge.nsn.qanda_hub.data.entities

import jakarta.persistence.*

@Entity
data class AnswerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val answer_id: Long? = null,
    val content: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    val question: QuestionEntity? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    val user: UserEntity? = null
)