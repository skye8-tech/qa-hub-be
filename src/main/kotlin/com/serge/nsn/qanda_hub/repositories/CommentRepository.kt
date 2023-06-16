package com.serge.nsn.qanda_hub.repositories

import com.serge.nsn.qanda_hub.entities.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<CommentEntity, Long> {
}