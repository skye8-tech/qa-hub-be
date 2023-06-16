package com.serge.nsn.qanda_hub.dto

class CommentDto(
    val comment_id: Long? = null,
    val content: String = ""
) {
    constructor(commentDto: CommentDto) : this(
        comment_id = commentDto.comment_id,
        content = commentDto.content
    )
}