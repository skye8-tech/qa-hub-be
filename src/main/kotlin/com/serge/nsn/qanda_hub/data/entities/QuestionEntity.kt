package com.serge.nsn.qanda_hub.data.entities

import jakarta.persistence.*

@Entity
data class QuestionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String = "",
    val content: String = "",

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @OneToMany(mappedBy = "question")
    val answers: List<AnswerEntity> = mutableListOf()
)