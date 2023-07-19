package com.serge.nsn.qahub.data.repositories

import com.serge.nsn.qahub.data.entities.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long>