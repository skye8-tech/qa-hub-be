package com.serge.nsn.qahub.api.controller

import com.serge.nsn.qahub.api.dto.QuestionDto
import com.serge.nsn.qahub.services.QuestionService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/question")
class QuestionController(
    private val questionService: QuestionService
) {

    @GetMapping("/all")
    fun getAll() = questionService.getAllQuestions()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = questionService.getQuestionById(id)

    @GetMapping("/user/{userId}")
    fun getByUserId(@PathVariable userId: Long): List<QuestionDto> {
        return questionService.getByUserId(userId)
    }

    @PostMapping("/ask")
    fun ask(@RequestBody question: QuestionDto, principal: Principal) = questionService.askQuestion(question, principal)
}