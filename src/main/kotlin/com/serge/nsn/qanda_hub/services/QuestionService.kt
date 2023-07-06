package com.serge.nsn.qanda_hub.services

import com.serge.nsn.qanda_hub.api.dto.QuestionDto
import com.serge.nsn.qanda_hub.api.dto.UserDto
import com.serge.nsn.qanda_hub.data.entities.QuestionEntity
import com.serge.nsn.qanda_hub.data.entities.UserEntity
import com.serge.nsn.qanda_hub.data.repositories.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val userService: UserService
) {
    fun askQuestion(dto: QuestionDto) =
        questionRepository
            .save(
                QuestionEntity(
                    dto.question_id,
                    dto.title,
                    dto.content,
                    UserEntity( userService.getById(2).user_id)
                    )

                )

    fun getAllQuestions() = questionRepository.findAll().map { QuestionDto(it) }
    fun getQuestionById(id: Long) = questionRepository.findById(id).map { QuestionDto(it) }

    fun  getByUserId(userId: Long): List<QuestionDto> {
        return questionRepository.findAllByUserId(userId).map { QuestionDto(it) }
    }
}