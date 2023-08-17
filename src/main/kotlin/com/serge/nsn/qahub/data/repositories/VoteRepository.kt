package com.serge.nsn.qahub.data.repositories

import com.serge.nsn.qahub.data.entities.VoteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository : JpaRepository<VoteEntity, Long>