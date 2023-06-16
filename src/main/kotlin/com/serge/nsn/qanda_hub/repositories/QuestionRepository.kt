package com.serge.nsn.qanda_hub.repositories

import com.serge.nsn.qanda_hub.entities.QuestionEntity
import org.aspectj.weaver.patterns.TypePatternQuestions.Question
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface QuestionRepository: JpaRepository <QuestionEntity, Long> {

}