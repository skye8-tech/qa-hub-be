package com.serge.nsn.qahub.api.controller

import com.serge.nsn.qahub.api.dto.AnswerDto
import com.serge.nsn.qahub.services.AnswerService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answer")
class AnswerController(
    private val answerService: AnswerService
) {
    @GetMapping("/all")
    fun getAll() = answerService.getAllAnswers()

    @GetMapping("/answer/{id}")
    fun getAnswer(@PathVariable id: Long) = answerService.getAnswerById(id)

    @PostMapping("/answer/{id}/provide-answer")
    fun answer(@RequestBody answer: AnswerDto, @PathVariable id: Long) = answerService.answerQuestion(answer, id)

}