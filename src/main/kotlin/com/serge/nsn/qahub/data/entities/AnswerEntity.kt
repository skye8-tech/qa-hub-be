package com.serge.nsn.qahub.data.entities

import jakarta.persistence.*

@Entity
data class AnswerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val content: String,
    val author: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    val question: QuestionEntity,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity
)