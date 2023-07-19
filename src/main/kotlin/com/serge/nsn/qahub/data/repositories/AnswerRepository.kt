package com.serge.nsn.qahub.data.repositories

import com.serge.nsn.qahub.data.entities.AnswerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<AnswerEntity, Long>