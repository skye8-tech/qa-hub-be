package com.serge.nsn.qahub.api.controller

import com.serge.nsn.qahub.services.PostService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/question")
class QuestionController(
    private val postService: PostService
) {

//    @GetMapping("/all")
//    fun getAll() = questionService.getAllQuestions()
//
//    @GetMapping("/{id}")
//    fun get(@PathVariable id: Long) = questionService.getQuestionById(id)
//
//    @GetMapping("/user/{userId}")
//    fun getByUserId(@PathVariable userId: Long): List<PostDto> {
//        return questionService.getByUserId(userId)
    }
//
//    @CrossOrigin(origins = ["http://localhost:4200/"])
////    @PostMapping("/ask")
////    fun ask(@RequestBody question: PostDto, principal: Principal) = questionService.askQuestion(question, principal)
////}