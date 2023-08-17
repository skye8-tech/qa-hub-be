package com.serge.nsn.qahub.api.controller
import com.serge.nsn.qahub.api.dto.PostDto
import com.serge.nsn.qahub.services.PostService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/post")
class PostController (
    private val postService: PostService,
    ) {

//        @GetMapping("/all")
//        fun getAll() = questionService.getAllQuestions()
//
//        @GetMapping("/{id}")
//        fun get(@PathVariable id: Long) = questionService.getQuestionById(id)
//
//        @GetMapping("/user/{userId}")
//        fun getByUserId(@PathVariable userId: Long): List<PostDto> {
//            return questionService.getByUserId(userId)
//        }

        @CrossOrigin(origins = ["http://localhost:4200/"])
        @PostMapping("/ask")
        fun ask(@RequestBody question: PostDto, principal: Principal) = postService.askQuestion(question, principal)

        @PostMapping("/{id}/answer")
        fun answer(@RequestBody answer: PostDto, @PathVariable id: Long, principal: Principal) = postService.answerQuestion(answer, principal, id)

        @PostMapping("/{postId}/comment")
        fun reply(@RequestBody comment: PostDto,
                  @PathVariable postId: Long,
                  principal: Principal) =
                    postService.comment(comment, postId, principal)

    }