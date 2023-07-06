package com.serge.nsn.qanda_hub.data.repositories

import com.serge.nsn.qanda_hub.data.entities.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<QuestionEntity, Long> {
    fun findAllByUserId(userId: Long): List<QuestionEntity>
}