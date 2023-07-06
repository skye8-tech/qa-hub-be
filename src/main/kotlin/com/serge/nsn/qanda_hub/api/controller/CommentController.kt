package com.serge.nsn.qanda_hub.api.controller

import com.serge.nsn.qanda_hub.api.dto.CommentDto
import com.serge.nsn.qanda_hub.services.CommentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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