package example.webapp.home

import com.codeborne.selenide.Condition.exist
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.matching.EqualToPattern
import example.greeting.api.extensions.GreetingDTO
import example.greeting.api.extensions.GreetingRequest
import example.testing.TestcontainersTest
import example.testing.extensions.json
import example.testing.extensions.s
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus.OK
import org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE

@DisplayName("Home page test")
class HomePageTest(@LocalServerPort port: Int, @Autowired val objectMapper: ObjectMapper) : TestcontainersTest(port) {

    @Test
    fun `should have home page`() {
        // setup
        stubFor(
            post("/api/v1/greetings/greet")
                .withRequestBody(
                    EqualToPattern(
                        objectMapper.json {
                            GreetingRequest {
                                name = "Home"
                            }
                        }
                    )
                )
                .withHeader("Content-Type", EqualToPattern(APPLICATION_JSON_VALUE))
                .willReturn(
                    aResponse()
                        .withStatus(OK.value())
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody(
                            objectMapper.json {
                                GreetingDTO {
                                    value = "Hello, Home!"
                                }
                            }
                        )
                )
        )

        // when
        Selenide.open(baseUrl)

        // then
        val text = s("#message")
            .shouldBe(exist, visible)
            .shouldHave(text("hello"))
            .shouldHave(text("home"))
            .text()

        // and
        assertThat(text).isEqualTo("Hello, Home!")
    }
}
