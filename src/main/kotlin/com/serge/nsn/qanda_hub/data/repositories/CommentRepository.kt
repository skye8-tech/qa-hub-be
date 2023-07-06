package com.serge.nsn.qanda_hub.data.repositories

import com.serge.nsn.qanda_hub.data.entities.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentEntity, Long>