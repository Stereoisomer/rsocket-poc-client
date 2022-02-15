package com.example.rsocket

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
    return rSocketRequester
      .route("get-yield")
      .data(HighYieldNoteRequest("bsc", name, 3000.0.toFloat(), "2022-04-01"))
      .retrieveMono(HighYieldNote::class.java)
  }

  @GetMapping(value = ["/create/{name}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
  fun feed(@PathVariable("name") name: String): Publisher<String?>? {
    return rSocketRequester
      .route("yield-create")
      .data(HighYieldNoteRequest("bsc", name, 3000.0.toFloat(), "2022-04-01"))
      .retrieveFlux(String::class.java)
  }
}

data class HighYieldNoteRequest(val network: String,
                                val underlying: String,
                                val strike: Float,
                                val expiry: String
)
