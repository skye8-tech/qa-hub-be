package com.serge.nsn.qanda_hub.data.entities

import jakarta.persistence.*


@Entity

data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String = "",
    val username: String = "",
    val password: String = "",
    @Column(unique = true)
    val email: String = "",
    val roles: String = "",

    @OneToMany(mappedBy = "user")
    val questions: List<QuestionEntity> = mutableListOf(),

    @OneToMany(mappedBy = "user")
    val answers: List<AnswerEntity> = mutableListOf(),

    @OneToMany(mappedBy = "user")
    val comments: List<CommentEntity> = mutableListOf()
)

