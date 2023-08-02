package com.serge.nsn.qahub.services

import com.serge.nsn.qahub.api.dto.AnswerDto
import com.serge.nsn.qahub.data.entities.AnswerEntity
import com.serge.nsn.qahub.data.repositories.AnswerRepository
import com.serge.nsn.qahub.data.repositories.QuestionRepository
import com.serge.nsn.qahub.data.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository
) {

    fun answerQuestion(dto: AnswerDto, id: Long, principal: Principal): ResponseEntity<AnswerDto> {
        if (questionRepository.findById(id).isEmpty) {
            throw NoSuchElementException("Question was doesn't exist/was deleted")
        } else {
            val answer = answerRepository.save(
                AnswerEntity
                    (
                    id = dto.id,
                    content = dto.content,
                    author = userRepository.findByEmail((principal.name)).get().name,
                    question = questionRepository.findById(id).get(),
                    user = userRepository.findByEmail(principal.name).get()
                )
            )
            return ResponseEntity(AnswerDto(answer), HttpStatus.CREATED)
        }
    }


    fun getAllAnswers(): MutableList<AnswerEntity> = answerRepository.findAll()
    fun getAnswerById(id: Long) = answerRepository.findById(id)
}