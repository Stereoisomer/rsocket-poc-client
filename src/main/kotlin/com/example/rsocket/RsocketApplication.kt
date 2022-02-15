package com.example.rsocket

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

private val logger = KotlinLogging.logger {}

@SpringBootApplication
class RsocketApplication

fun main(args: Array<String>) {
	logger.info("Starting RSocket Application")
	runApplication<RsocketApplication>(*args)
}
