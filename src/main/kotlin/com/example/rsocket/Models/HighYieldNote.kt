package com.example.rsocket

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
data class HighYieldNote (
    @Id val id: String,
    val network: String,
    val underlying: String,
    val strike: Float,
    val expiry: LocalDate
) {

    init {
        println("HighYieldNote:Init")
    }

    //Getters and setters
    override fun toString(): String {
        return "HighYieldNote [id=$id, strike=$strike, underlying=$underlying, expiry=$expiry"
    }
}

