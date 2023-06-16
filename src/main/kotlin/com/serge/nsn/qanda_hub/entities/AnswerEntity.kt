package com.serge.nsn.qanda_hub.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class AnswerEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val answer_id: Long? = null,
    val content: String,
        )