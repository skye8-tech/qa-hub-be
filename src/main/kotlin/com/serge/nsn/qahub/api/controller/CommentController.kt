package com.serge.nsn.qahub.api.controller

import com.serge.nsn.qahub.api.dto.CommentDto
import com.serge.nsn.qahub.services.CommentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comment")
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping("/all")
    fun allComments() = commentService.getAllComments()

    @GetMapping("/{id}")
    fun getComment(@PathVariable id: Long) = commentService.getComment(id)

    @PostMapping("/reply")
    fun reply(@RequestBody comment: CommentDto) = commentService.comment(comment)

}