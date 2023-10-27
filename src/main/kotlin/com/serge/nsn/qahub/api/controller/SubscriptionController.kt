package com.serge.nsn.qahub.api.controller

import com.serge.nsn.qahub.api.dto.SubscriptionDto
import com.serge.nsn.qahub.services.SubscriptionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/subscribe")
class SubscriptionController(
    private val subscriptionService: SubscriptionService
) {
    @PostMapping("/new")
    fun subscribeUser(@RequestBody subscriptionDto: SubscriptionDto, principal: Principal): SubscriptionDto {
        return subscriptionService.subscribeUser(subscriptionDto, principal)
    }
}