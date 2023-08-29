package com.serge.nsn.qahub.data.repositories

import com.serge.nsn.qahub.data.entities.PostEntity
import com.serge.nsn.qahub.data.entities.PostType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepository : JpaRepository<PostEntity, Long> {


    fun findAllByUserId(userId: Long): List<PostEntity>
    fun findAllByType(type: PostType): List<PostEntity>
    fun findAllByTypeAndUserId(type: PostType, uId: Long): List<PostEntity>
    fun findAllByTypeAndPost(type: PostType, post: PostEntity): List<PostEntity>
    fun findAllCommentsByTypeAndPost(type: PostType, post: PostEntity): List<PostEntity>
//    fun deleteByTypeandPostId(comment: PostType, postId: Long)
}