package com.serge.nsn.qahub.data.entities

import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Subscription(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    @ManyToOne
    val user: UserEntity,
    @Enumerated(EnumType.STRING)
    val plan: SubscriptionType,
    val startDate: LocalDate,
    val endDate: LocalDate
)