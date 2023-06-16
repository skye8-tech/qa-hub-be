package com.serge.nsn.qanda_hub.services

import com.serge.nsn.qanda_hub.dto.QuestionDto
import com.serge.nsn.qanda_hub.entities.QuestionEntity
import com.serge.nsn.qanda_hub.entities.UserEntity
import com.serge.nsn.qanda_hub.repositories.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository
){
    fun askQuestion(dto: QuestionDto) =
        questionRepository
            .save(
                QuestionEntity(
                    dto.question_id,
                    dto.title,
                    dto.content,
                    UserEntity(user_id = dto.question_id, name = "", username = "", email = "", password = "")
                )
            )
    fun getAllQuestions() = questionRepository.findAll()
    fun getQuestionById(id: Long) = questionRepository.findById(id)
}