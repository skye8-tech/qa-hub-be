package com.serge.nsn.qanda_hub.api.controller

import com.serge.nsn.qanda_hub.api.dto.QuestionDto
import com.serge.nsn.qanda_hub.services.QuestionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
        return questionService.getByUserId(userId);
    }

    @PostMapping("/ask")
    fun ask(@RequestBody question: QuestionDto) = questionService.askQuestion(question)
}