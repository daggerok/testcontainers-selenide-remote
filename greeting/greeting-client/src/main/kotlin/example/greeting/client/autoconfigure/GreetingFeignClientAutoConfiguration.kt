package example.greeting.client.autoconfigure

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import example.greeting.client.GreetingClient
import feign.Feign
import feign.Logger
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.slf4j.Slf4jLogger
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@Suppress("HttpUrlsUsage")
@EnableConfigurationProperties(GreetingProperties::class)
class GreetingFeignClientAutoConfiguration {

    @Bean
    fun feignOrdersClient(props: GreetingProperties): GreetingClient =
        Feign.builder()
            .logger(Slf4jLogger())
            .logLevel(Logger.Level.FULL)
            .encoder(JacksonEncoder(listOf(JavaTimeModule())))
            .decoder(JacksonDecoder(listOf(JavaTimeModule())))
            .target(GreetingClient::class.java, "http://${props.host}:${props.port}")
}
