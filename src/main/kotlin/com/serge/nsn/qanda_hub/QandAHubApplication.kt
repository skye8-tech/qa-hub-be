package com.serge.nsn.qanda_hub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])

class QandAHubApplication

fun main(args: Array<String>) {
    runApplication<QandAHubApplication>(*args)
}
