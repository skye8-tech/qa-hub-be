package com.serge.nsn.qahub.services

import com.serge.nsn.qahub.api.dto.SubscriptionDto
import com.serge.nsn.qahub.data.entities.Subscription
import com.serge.nsn.qahub.data.entities.SubscriptionType
import com.serge.nsn.qahub.data.repositories.SubscriptionRepository
import com.serge.nsn.qahub.data.repositories.UserRepository
import org.springframework.stereotype.Service
import java.security.Principal
import java.time.LocalDate

@Service
class SubscriptionService(
    private val subscriptionRepository: SubscriptionRepository,
    private val userRepository: UserRepository,
) {
//    fun subscribeUser(user: UserEntity, plan: SubscriptionPlan, startDate: LocalDate): Subscription {
//        val endDate = startDate.plusMonths(plan.durationInMonths.toLong())
//        val subscription = Subscription(user = user, plan = plan, startDate = startDate, endDate = endDate)
//        return subscriptionRepository.save(subscription)

    fun subscribeUser(subscriptionDto: SubscriptionDto, principal: Principal): SubscriptionDto {
        val subscription = Subscription(
            user = userRepository.findByEmail(principal.name).get(),
            plan = SubscriptionType.valueOf(subscriptionDto.plan),
            startDate = LocalDate.now(),
            endDate = LocalDate.now().plusMonths(SubscriptionType.valueOf(subscriptionDto.plan).durationInMonths)
        )

        return SubscriptionDto(
            subscriptionRepository.save(subscription)
        )

    }
}