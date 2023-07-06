package com.serge.nsn.qanda_hub.api.controller

import com.serge.nsn.qanda_hub.api.dto.AnswerDto
import com.serge.nsn.qanda_hub.services.AnswerService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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