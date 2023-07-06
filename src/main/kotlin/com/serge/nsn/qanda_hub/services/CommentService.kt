package com.serge.nsn.qanda_hub.services

import com.serge.nsn.qanda_hub.api.dto.CommentDto
import com.serge.nsn.qanda_hub.data.entities.CommentEntity
import com.serge.nsn.qanda_hub.data.repositories.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun comment(dto: CommentDto) = commentRepository.save(CommentEntity(dto.comment_id, dto.content))
    fun getAllComments() = commentRepository.findAll()
    fun getComment(id: Long) = commentRepository.findById(id)
}