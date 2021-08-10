package example.greeting.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.matching.EqualToPattern
import example.greeting.api.extensions.GreetingDTO
import example.greeting.api.extensions.GreetingRequest
import example.testing.WireMockTest
import example.testing.extensions.json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE

@SpringBootApplication
internal class GreetTestApp

@DisplayName("Greet Feign Client operation WireMock testing")
@ContextConfiguration(classes = [GreetTestApp::class])
class GreetTest @Autowired constructor(val objectMapper: ObjectMapper, val greetingClient: GreetingClient) :
    WireMockTest() {

    @Test
    fun `should greet`() {
        // given
        val request = GreetingRequest {
            name = "Maksimko"
        }

        // and
        stubFor(
            post("/api/v1/greetings/greet")
                .withHeader("Content-Type", EqualToPattern(APPLICATION_JSON_VALUE))
                .withRequestBody(
                    EqualToPattern(
                        objectMapper.json {
                            request
                        }
                    )
                )
                .willReturn(
                    aResponse()
                        .withStatus(HttpStatus.CREATED.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(
                            objectMapper.json {
                                GreetingDTO {
                                    value = "Hello, Maksimko!"
                                }
                            }
                        )
                )
        )

        // when
        val response = greetingClient.greet(request)

        // then
        assertThat(response.value).isEqualTo("Hello, Maksimko!")
    }
}
