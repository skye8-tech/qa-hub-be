package com.serge.nsn.qahub.services

import com.serge.nsn.qahub.api.dto.QuestionDto
import com.serge.nsn.qahub.data.entities.QuestionEntity
import com.serge.nsn.qahub.data.entities.UserEntity
import com.serge.nsn.qahub.data.repositories.QuestionRepository
import com.serge.nsn.qahub.data.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository
) {
    fun askQuestion(dto: QuestionDto, principal: Principal): ResponseEntity<QuestionDto> {
        val questionAuthor: UserEntity = userRepository.findByEmail(principal.name).get()
        val askedQuestion = questionRepository
            .save(
                QuestionEntity(
                    id = dto.id,
                    title = dto.title,
                    content = dto.content,
                    author = questionAuthor.name,
                    user = questionAuthor
                )

            )

        return ResponseEntity(QuestionDto(askedQuestion), HttpStatus.CREATED)
    }

    fun getAllQuestions() = questionRepository.findAll().map { QuestionDto(it) }
    fun getQuestionById(id: Long) = questionRepository.findById(id).map { QuestionDto(it) }

    fun getByUserId(userId: Long): List<QuestionDto> {
        return questionRepository.findAllByUserId(userId).map { QuestionDto(it) }
    }

}