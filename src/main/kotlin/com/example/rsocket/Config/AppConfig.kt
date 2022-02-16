package com.example.rsocket

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import reactor.util.retry.Retry
import java.time.Duration

import org.springframework.http.codec.cbor.Jackson2CborDecoder
import org.springframework.http.codec.cbor.Jackson2CborEncoder

@Configuration
public class AppConfiguration {
  companion object {
    @Bean
    fun getRSocketRequester(strategies: RSocketStrategies): RSocketRequester {
        val builder: RSocketRequester.Builder = RSocketRequester.builder()

        return builder
          .rsocketConnector{
              rSocketConnector ->
            rSocketConnector.reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2)))
          }
          .rsocketStrategies(strategies)
          .tcp("localhost", 8000)
    }

    @Bean
    fun rsocketStrategies(): RSocketStrategies {
      return RSocketStrategies.builder()
        .decoder(Jackson2CborDecoder())
        .encoder(Jackson2CborEncoder())
        .dataBufferFactory(DefaultDataBufferFactory(true))
        .build()
    }
  }
}