package com.serge.nsn.qahub.api.dto

import com.serge.nsn.qahub.data.entities.PostEntity

class PostDto(
    val id: Long? = null,
    val title: String? = "",
    val content: String = "",
    val author: String = "",
    val tag: String?


) {
    constructor(postEntity: PostEntity) : this(
        id = postEntity.id,
        title = postEntity.title,
        content = postEntity.content,
        author = postEntity.user.name,
        tag = postEntity.tag,


    )
}