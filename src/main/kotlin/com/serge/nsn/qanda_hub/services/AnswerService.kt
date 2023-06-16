package com.serge.nsn.qanda_hub.services

import com.serge.nsn.qanda_hub.dto.AnswerDto
import com.serge.nsn.qanda_hub.entities.AnswerEntity
import com.serge.nsn.qanda_hub.repositories.AnswerRepository
import com.serge.nsn.qanda_hub.repositories.QuestionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val questionService: QuestionService
) {

    fun answerQuestion(dto: AnswerDto, id: Long): AnswerEntity{
        if(questionService.getQuestionById(id)==null){
            throw NoSuchElementException("Question was doesn't exist/was deleted")
        }
        else{
            return answerRepository.save(AnswerEntity(dto.answer_id, dto.content))
        }
    }


    fun getAllAnswers(id:Long) = answerRepository.findAllById(listOf(id))
}