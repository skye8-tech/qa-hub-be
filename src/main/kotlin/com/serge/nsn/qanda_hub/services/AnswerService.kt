package com.serge.nsn.qanda_hub.services

import com.serge.nsn.qanda_hub.api.dto.AnswerDto
import com.serge.nsn.qanda_hub.data.entities.AnswerEntity
import com.serge.nsn.qanda_hub.data.repositories.AnswerRepository
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
            return answerRepository.save(AnswerEntity(dto.answer_id, dto.content))
        }
    }


    fun getAllAnswers(): MutableList<AnswerEntity> = answerRepository.findAll()
    fun getAnswerById(id: Long) = answerRepository.findById(id)
}