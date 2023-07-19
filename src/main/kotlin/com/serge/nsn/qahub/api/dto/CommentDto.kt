package com.serge.nsn.qahub.api.dto

class CommentDto(
    val comment_id: Long? = null,
    val content: String = ""
) {
    constructor(commentDto: CommentDto) : this(
        comment_id = commentDto.comment_id,
        content = commentDto.content
    )
}