package com.serge.nsn.qahub.api.controller

import com.serge.nsn.qahub.api.dto.PostDto
import com.serge.nsn.qahub.data.entities.PostType
import com.serge.nsn.qahub.services.PostService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/post")
class PostController(
    private val postService: PostService,
) {

    @GetMapping("/all")
    fun getAll() = postService.getAllPosts()

    //Delete post
    @DeleteMapping("/{postId}/delete")
    fun deletePost(@PathVariable postId: Long) = postService.deletePost(postId)


    //Post Question
    @CrossOrigin(origins = ["http://localhost:4200/"])
    @PostMapping("/ask")
    fun ask(@RequestBody question: PostDto, principal: Principal) = postService.askQuestion(question, principal)

    //Get Question by Id
    @GetMapping("/question/{qId}")
    fun ask(@PathVariable qId: Long) = postService.getQuestionByID(qId)

    //Get all questions
    @GetMapping("/questions")
    fun ask() = postService.getAllQuestions()

    //Get Questions asked by user
    @GetMapping("/questions/{uId}")
    fun userQuestions(@PathVariable uId: Long) = postService.getPostByTypeAndUserId(PostType.QUESTION, uId)


    //Get posts by user
    @GetMapping("/posts/{uId}")
    fun questionsByUser(@PathVariable uId: Long) = postService.allPostsByUserId(uId)


    //Answer Question
    @PostMapping("/{qId}/answer")
    fun answer(@RequestBody answer: PostDto, @PathVariable qId: Long, principal: Principal) =
        postService.answerQuestion(answer, principal, qId)


    //All Answers to question
    @GetMapping("/{qId}/answers")
    fun answers(@PathVariable qId: Long) = postService.getAnswersByQuestionId(PostType.ANSWER, qId)


    @PostMapping("/{postId}/comment")
    fun reply(
        @RequestBody comment: PostDto,
        @PathVariable postId: Long,
        principal: Principal
    ) =
        postService.comment(comment, postId, principal)


    //All comments under post
    @GetMapping("/{postId}/comments")
    fun comments(@PathVariable postId: Long) = postService.getAllComments(postId)

    //upvote
    @PostMapping("/{postId}/upvote")
    fun upvote(@PathVariable postId: Long, principal: Principal) = postService.upVote(postId, principal)

    //downvote
    @PostMapping("/{postId}/downvote")
    fun downvote(@PathVariable postId: Long, principal: Principal) = postService.downVote(postId, principal)
}