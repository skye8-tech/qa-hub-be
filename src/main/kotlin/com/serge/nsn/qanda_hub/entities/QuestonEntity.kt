package com.serge.nsn.qanda_hub.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
data class QuestionEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val question_id: Long?= null,
    val title: String = "",
    val content: String = "",

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    @OneToMany(mappedBy = "question")
    val answers: List<AnswerEntity> = mutableListOf(),


)