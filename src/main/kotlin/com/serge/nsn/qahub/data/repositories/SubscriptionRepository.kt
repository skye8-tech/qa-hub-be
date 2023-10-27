package com.serge.nsn.qahub.data.repositories

import com.serge.nsn.qahub.data.entities.Subscription
import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionRepository : JpaRepository<Subscription, Long> {
}