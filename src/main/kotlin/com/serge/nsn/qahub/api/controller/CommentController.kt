package com.serge.nsn.qahub.api.controller

import com.serge.nsn.qahub.api.dto.CommentDto
import com.serge.nsn.qahub.services.CommentService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/comment")
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping("/all")
    fun allComments() = commentService.getAllComments()

    @GetMapping("/{id}")
    fun getComment(@PathVariable id: Long) = commentService.getComment(id)

    @PostMapping("/{question_id}/{answer_id}/reply")
    fun reply(@RequestBody comment: CommentDto,
              @PathVariable question_id: Long,
              @PathVariable answer_id: Long,
              principal: Principal) =
        commentService
            .comment(comment, principal,question_id, answer_id)

}