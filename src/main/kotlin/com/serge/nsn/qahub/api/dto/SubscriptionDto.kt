package com.serge.nsn.qahub.api.dto

import com.serge.nsn.qahub.data.entities.Subscription
import java.time.LocalDate

class SubscriptionDto(
    val id: Long = 0,
    val user: String?,
    val plan: String,
    val startDate: LocalDate?,
    val endDate: LocalDate?
) {
    constructor(subscription: Subscription) : this(
        id = subscription.id,
        user = subscription.user.username,
        plan = subscription.plan.name,
        startDate = subscription.startDate,
        endDate = subscription.endDate
    )
}