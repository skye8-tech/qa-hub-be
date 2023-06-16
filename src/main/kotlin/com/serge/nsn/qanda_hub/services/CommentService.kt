package com.serge.nsn.qanda_hub.services

import com.serge.nsn.qanda_hub.dto.CommentDto
import com.serge.nsn.qanda_hub.entities.CommentEntity
import com.serge.nsn.qanda_hub.repositories.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun comment(dto: CommentDto) = commentRepository.save(CommentEntity(dto.comment_id, dto.content))
    fun getAllComments(id: Long) = commentRepository.findAllById(listOf(id))
}