//import com.serge.nsn.qahub.data.entities.PostType
//import jakarta.persistence.*

package com.serge.nsn.qahub.data.entities
import jakarta.persistence.*
@Entity
data class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val title: String? = "",
    val content: String = "",
    val tag: String? = "",



    @Enumerated(EnumType.STRING)
    val type: PostType,



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post")
    val post: PostEntity?,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,

    @OneToMany(mappedBy = "post")
    val answers: List<PostEntity> = listOf(),

    @OneToMany(mappedBy = "id")
    val comments: List<PostEntity> = listOf()

    )