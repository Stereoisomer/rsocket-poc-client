package com.example.rsocket

import lombok.Getter
import lombok.ToString
import org.reactivestreams.Publisher
import org.springframework.http.MediaType
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class RSocketRestController(private val rSocketRequester: RSocketRequester) {
  @GetMapping(value = ["/yield/{name}"])
  fun current(@PathVariable("name") name: String): Publisher<HighYieldNote> {
    println("Inside get token")
    println(name)
    return rSocketRequester
      .route("get-yield")
      .data(name)
//      .data(HighYieldNoteRequest("bsc", name, 3000.0.toFloat(), "2022-04-01"))
      .retrieveMono(HighYieldNote::class.java)
      .doOnSuccess {
        println(it)
      }.doOnError {
        println(it.message)
      }.doOnNext {
        println(it)
      }
  }

  @GetMapping(value = ["/create/{name}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
  fun feed(@PathVariable("name") name: String) {
    println("Inside create token")
    println(name)
    rSocketRequester
      .route("yield-create")
      .data(name)
//      .data(HighYieldNoteRequest("bsc", name, 3000.0.toFloat(), "2022-04-01"))
      .retrieveMono(String::class.java)
      .doOnSuccess {
        println(it)
      }.doOnError {
        println(it.message)
      }.doOnNext {
        println(it)
      }
  }
}

@Getter
@ToString
data class HighYieldNoteRequest(val network: String,
                                val underlying: String,
                                val strike: Float,
                                val expiry: String
) {
  override fun toString(): String {
    return "HighYieldNoteRequest [network=$network, strike=$strike, underlying=$underlying, expiry=$expiry"
  }
}
