package com.serge.nsn.qanda_hub.data.repositories

import com.serge.nsn.qanda_hub.data.entities.AnswerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<AnswerEntity, Long>