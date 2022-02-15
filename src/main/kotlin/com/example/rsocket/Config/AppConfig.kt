package com.example.rsocket

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.util.MimeTypeUtils
import reactor.util.retry.Retry
import java.time.Duration

@Configuration
public class AppConfiguration {
  companion object {
    @Bean
    fun getRSocketRequester(): RSocketRequester {
        val builder: RSocketRequester.Builder = RSocketRequester.builder()

        return builder
          .rsocketConnector{
              rSocketConnector ->
            rSocketConnector.reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2)))
          }
//          .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
          .tcp("localhost", 8000);
    }
  }
}