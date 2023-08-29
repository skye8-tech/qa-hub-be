package com.serge.nsn.qahub.data.entities

enum class SubscriptionType(val plan: String, val durationInMonths: Long, val price: Double) {
    FREE_TRIAL("Free Trial", 1, 0.0),
    ONE_MONTH("1 Month Plan", 1, 800.00),
    ONE_YEAR("1 Year Plan", 12, 10000.00)
}
