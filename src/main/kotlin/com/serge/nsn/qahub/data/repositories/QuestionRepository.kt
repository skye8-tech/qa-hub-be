package com.serge.nsn.qahub.data.repositories

import com.serge.nsn.qahub.data.entities.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<QuestionEntity, Long> {
    fun findAllByUserId(userId: Long): List<QuestionEntity>
}