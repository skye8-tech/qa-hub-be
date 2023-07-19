package com.serge.nsn.qahub.services

import com.serge.nsn.qahub.api.dto.AnswerDto
import com.serge.nsn.qahub.data.entities.AnswerEntity
import com.serge.nsn.qahub.data.repositories.AnswerRepository
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val questionService: QuestionService
) {

    fun answerQuestion(dto: AnswerDto, id: Long): AnswerEntity {
        if (questionService.getQuestionById(id).isEmpty) {
            throw NoSuchElementException("Question was doesn't exist/was deleted")
        } else {
            return answerRepository.save(AnswerEntity(dto.id, dto.content))
        }
    }


    fun getAllAnswers(): MutableList<AnswerEntity> = answerRepository.findAll()
    fun getAnswerById(id: Long) = answerRepository.findById(id)
}