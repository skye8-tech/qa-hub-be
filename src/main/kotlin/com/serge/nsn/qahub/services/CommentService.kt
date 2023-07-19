package com.serge.nsn.qahub.services

import com.serge.nsn.qahub.api.dto.CommentDto
import com.serge.nsn.qahub.data.entities.CommentEntity
import com.serge.nsn.qahub.data.repositories.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun comment(dto: CommentDto) = commentRepository.save(CommentEntity(dto.comment_id, dto.content))
    fun getAllComments() = commentRepository.findAll()
    fun getComment(id: Long) = commentRepository.findById(id)
}