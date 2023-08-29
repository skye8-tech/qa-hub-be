package com.serge.nsn.qahub.services

import com.serge.nsn.qahub.api.dto.PostDto
import com.serge.nsn.qahub.data.entities.*
import com.serge.nsn.qahub.data.repositories.PostRepository
import com.serge.nsn.qahub.data.repositories.UserRepository
import com.serge.nsn.qahub.data.repositories.VoteRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val voteRepository: VoteRepository
) {

    @GetMapping("/all")
    fun getAllPosts(): List<PostDto> {
        return postRepository.findAll().map { post -> PostDto(post) }
    }

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

                post = postRepository.findById(postId).get(),
//                    questionId = questionRepository.findById(qID).get()
            )
        )

        return ResponseEntity(PostDto(commentResponse), HttpStatus.CREATED)
    }

    fun getQuestionByID(qId: Long): PostDto {
        val post = postRepository.findById(qId)
        if (post.isEmpty) {
            throw PostNotFoundException("The post you are requesting for does not longer exist")
        } else if (post.get().type != PostType.QUESTION) {
            throw InvalidPostTypeException("The post you are requesting for is not a question")
        }

        val postDto = PostDto(post.get())
        return postDto
    }

    fun getAllQuestions(): List<PostDto> {
        val allQuestions = postRepository.findAllByType(PostType.QUESTION)
        return allQuestions.map { question -> PostDto(question) }
    }

    fun allPostsByUserId(uId: Long): List<PostDto> {
        val posts = postRepository.findAllByUserId(uId)
        return posts.map { post -> PostDto(post) }
    }

    fun getPostByTypeAndUserId(postType: PostType, uId: Long): List<PostDto> {
        val allQuestions = postRepository.findAllByTypeAndUserId(postType, uId)
        return allQuestions.map { question -> PostDto(question) }
    }

    fun getAnswersByQuestionId(postType: PostType = PostType.ANSWER, qId: Long): List<PostDto> {
        val answers = postRepository.findAllByTypeAndPost(postType, postRepository.findById(qId).get())
        return answers.map { answer ->
            PostDto(answer)
        }
    }

    fun getAllComments(postId: Long): List<PostDto> {
        val comments =
            postRepository.findAllCommentsByTypeAndPost(PostType.COMMENT, postRepository.findById(postId).get())
        return comments.map { comment -> PostDto(comment) }
    }

    fun deletePost(postId: Long): HttpStatus {
        val post = postRepository.findById(postId)
        post.ifPresent { p ->
            p.answers.forEach(postRepository::delete)
            p.comments.forEach(postRepository::delete)
        }
        return if (post.isEmpty) {
            HttpStatus.NOT_FOUND
        } else {
            HttpStatus.OK
        }
    }

    fun upVote(postId: Long, principal: Principal): ResponseEntity<String> {

        val existingVote =
            voteRepository.findByPostIdAndUserId(postId, userRepository.findByEmail(principal.name).get().id)
        if (existingVote != null && existingVote.type == VoteType.UP) {
            return ResponseEntity("You can't vote twice", HttpStatus.NOT_ACCEPTABLE)
        } else if (existingVote != null && existingVote.type == VoteType.DOWN) {
            voteRepository.delete(existingVote)
            voteRepository.save(
                VoteEntity(
                    type = VoteType.UP,
                    post = postRepository.findById(postId).get(),
                    user = userRepository.findByEmail(principal.name).get()
                )
            )
            return ResponseEntity("Vote cast Successful!", HttpStatus.OK)
        } else {
            voteRepository.save(
                VoteEntity(
                    type = VoteType.UP,
                    post = postRepository.findById(postId).get(),
                    user = userRepository.findByEmail(principal.name).get()
                )
            )
            return ResponseEntity("Vote cast Successful!", HttpStatus.OK)
        }
    }


fun downVote(postId: Long, principal: Principal): ResponseEntity<String> {

    val existingVote =
        voteRepository.findByPostIdAndUserId(postId, userRepository.findByEmail(principal.name).get().id)
    if (existingVote != null && existingVote.type == VoteType.DOWN) {
        return ResponseEntity("You can't vote twice", HttpStatus.NOT_ACCEPTABLE)
    } else if (existingVote != null && existingVote.type == VoteType.UP) {
        voteRepository.delete(existingVote)
        voteRepository.save(
            VoteEntity(
                type = VoteType.DOWN,
                post = postRepository.findById(postId).get(),
                user = userRepository.findByEmail(principal.name).get()
            )
        )
        return ResponseEntity("Vote cast Successful!", HttpStatus.OK)
    } else {
        voteRepository.save(
            VoteEntity(
                type = VoteType.DOWN,
                post = postRepository.findById(postId).get(),
                user = userRepository.findByEmail(principal.name).get()
            )
        )
        return ResponseEntity("Vote cast Successful!", HttpStatus.OK)
    }
}


class InvalidPostTypeException(message: String) : RuntimeException(message)
class PostNotFoundException(message: String) : RuntimeException(message)

}
//    fun getAllQuestions() = questionRepository.findAll().map { PostDto(it) }
//    fun getQuestionById(id: Long) = questionRepository.findById(id).map { PostDto(it) }
//
//    fun getByUserId(userId: Long): List<PostDto> {
//        return questionRepository.findAllByUserId(userId).map { PostDto(it) }
//    }

//}