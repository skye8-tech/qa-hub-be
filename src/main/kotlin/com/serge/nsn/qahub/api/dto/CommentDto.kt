package com.serge.nsn.qahub.api.dto

import com.serge.nsn.qahub.data.entities.CommentEntity

class CommentDto(
    val comment_id: Long? = null,
    val content: String = "",
    val author: String = ""
) {
    constructor(commentEntity: CommentEntity) : this(
        comment_id = commentEntity.id,
        content = commentEntity.content,
        author = commentEntity.author
    )
}