package com.serge.nsn.qanda_hub.entities

import jakarta.persistence.*
import org.aspectj.weaver.patterns.TypePatternQuestions


@Entity

data class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val user_id: Long?=null,
    val name: String = "",
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val role: String = "",

    @OneToMany(mappedBy = "user")
    val questions: List<QuestionEntity> = mutableListOf(),

    @OneToMany(mappedBy = "user")
    val answers: List<AnswerEntity> = mutableListOf(),

    @OneToMany(mappedBy = "user")
    val comments: List<CommentEntity> = mutableListOf()
)

