//package com.serge.nsn.qahub.api.controller
//
//import com.serge.nsn.qahub.api.dto.AnswerDto
//import com.serge.nsn.qahub.services.AnswerService
//import lombok.RequiredArgsConstructor
//import org.springframework.web.bind.annotation.*
//import java.security.Principal
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/answer")
//class AnswerController(
//    private val answerService: AnswerService
//) {
//    @GetMapping("/all")
//    fun getAll() = answerService.getAllAnswers()
//
//    @GetMapping("/{id}")
//    fun getAnswer(@PathVariable id: Long) = answerService.getAnswerById(id)
//
//    @PostMapping("/{id}/answer")
//    fun answer(@RequestBody answer: AnswerDto, @PathVariable id: Long, principal: Principal) = answerService.answerQuestion(answer, id, principal)
//
//}