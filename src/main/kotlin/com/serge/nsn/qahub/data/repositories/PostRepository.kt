package com.serge.nsn.qahub.data.repositories

import com.serge.nsn.qahub.data.entities.PostEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findAllByUserId(userId: Long): List<PostEntity>

}