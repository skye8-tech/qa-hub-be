package com.serge.nsn.qanda_hub.entities

import jakarta.persistence.*

@Entity
data class CommentEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val comment_id: Long? = null,
    val content: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    val question: QuestionEntity? = null,


    )