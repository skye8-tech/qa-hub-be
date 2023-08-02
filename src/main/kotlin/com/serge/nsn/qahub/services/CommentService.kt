package com.serge.nsn.qahub.services

import com.serge.nsn.qahub.api.dto.CommentDto
import com.serge.nsn.qahub.data.entities.CommentEntity
import com.serge.nsn.qahub.data.repositories.CommentRepository
import com.serge.nsn.qahub.data.repositories.QuestionRepository
import com.serge.nsn.qahub.data.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val questionRepository: QuestionRepository
) {
    fun comment(
        dto: CommentDto,
        principal: Principal,
        qID: Long,
        aID: Long
    ): ResponseEntity<CommentDto> {
        val author = userRepository.findByEmail(principal.name).get()
        val comment = commentRepository.save(
            CommentEntity(
                id = dto.comment_id,
                content = dto.content,
                author = author.name,
                question = questionRepository.findById(qID).get(),
                user = author
            )
        )

        return ResponseEntity(CommentDto(comment), HttpStatus.CREATED)
    }

    fun getAllComments() = commentRepository.findAll()
    fun getComment(id: Long) = commentRepository.findById(id)
}