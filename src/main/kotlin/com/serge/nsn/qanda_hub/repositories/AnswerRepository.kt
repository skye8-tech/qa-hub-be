package com.serge.nsn.qanda_hub.repositories

import com.serge.nsn.qanda_hub.entities.AnswerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository: JpaRepository<AnswerEntity, Long> {

}