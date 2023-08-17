package com.serge.nsn.qahub.services

import com.serge.nsn.qahub.api.dto.PostDto
import com.serge.nsn.qahub.data.entities.PostEntity
import com.serge.nsn.qahub.data.entities.PostType
import com.serge.nsn.qahub.data.entities.UserEntity
import com.serge.nsn.qahub.data.repositories.PostRepository
import com.serge.nsn.qahub.data.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) {
    fun askQuestion(dto: PostDto, principal: Principal): ResponseEntity<PostDto> {
        val questionAuthor: UserEntity = userRepository.findByEmail(principal.name).get()
        val askedQuestion = postRepository
            .save(
                PostEntity(
                    id = dto.id,
                    title = dto.title,
                    content = dto.content,
                    tag = dto.tag,
                    user = questionAuthor,
                    type = PostType.QUESTION,
                    postId =  null,
                    question = null,
                    post = null
                )

            )

        return ResponseEntity(PostDto(askedQuestion), HttpStatus.CREATED)
    }
    fun answerQuestion(dto: PostDto, principal: Principal, qID: Long): ResponseEntity<PostDto> {
        val answerAuthor: UserEntity = userRepository.findByEmail(principal.name).get()
        val askedQuestion = postRepository
            .save(
                PostEntity(
                    id = dto.id,
                    content = dto.content,
                    user = answerAuthor,
                    type = PostType.ANSWER,
//                    tag="",
                    postId = qID,
                    question = postRepository.findById(qID).get(),
                    post = postRepository.findById(qID).get()
//                    questionId = questionRepository.findById(qID).get()
                )

            )

        return ResponseEntity(PostDto(askedQuestion), HttpStatus.CREATED)
    }
    fun comment(dto: PostDto, postId: Long, principal: Principal): ResponseEntity<PostDto> {
        val commentAuthor: UserEntity = userRepository.findByEmail(principal.name).get()
        val commentResponse = postRepository.save(
            PostEntity(
                id = dto.id,
                content = dto.content,
                user = commentAuthor,
                type = PostType.COMMENT,
//                    tag="",
                postId = postId,
                post = postRepository.findById(postId).get(),
                question = null,
//                    questionId = questionRepository.findById(qID).get()
            )
        )

        return ResponseEntity(PostDto(commentResponse), HttpStatus.CREATED)
    }

    }
//    fun getAllQuestions() = questionRepository.findAll().map { PostDto(it) }
//    fun getQuestionById(id: Long) = questionRepository.findById(id).map { PostDto(it) }
//
//    fun getByUserId(userId: Long): List<PostDto> {
//        return questionRepository.findAllByUserId(userId).map { PostDto(it) }
//    }

//}