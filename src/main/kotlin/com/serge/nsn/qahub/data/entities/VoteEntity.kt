package com.serge.nsn.qahub.data.entities

import jakarta.persistence.*

@Entity
data class VoteEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: VoteType,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    val postEntity: PostEntity,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity
)