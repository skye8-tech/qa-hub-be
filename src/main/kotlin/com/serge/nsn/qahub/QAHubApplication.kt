package com.serge.nsn.qahub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication

class QAHubApplication

fun main(args: Array<String>) {
    runApplication<QAHubApplication>(*args)
}

//typealias  Id = UUID
//val id = Id()