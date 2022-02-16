package com.example.rsocket

import java.time.LocalDate

data class HighYieldNote (
    val id: String,
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

