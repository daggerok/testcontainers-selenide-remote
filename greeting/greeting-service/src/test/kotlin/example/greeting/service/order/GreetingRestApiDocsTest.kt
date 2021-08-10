package example.greeting.service.order

import com.fasterxml.jackson.databind.ObjectMapper
import example.greeting.api.extensions.GreetingRequest
import example.greeting.client.autoconfigure.GreetingProperties
import example.testing.RestApiDocumentationTest
import example.testing.extensions.json
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@DisplayName("Greetings REST API Documentation test")
@Suppress("SpringJavaInjectionPointsAutowiringInspection", "HttpUrlsUsage")
class GreetingRestApiDocsTest @Autowired constructor(
    @LocalServerPort val port: Int,
    val objectMapper: ObjectMapper,
    val props: GreetingProperties,
) : RestApiDocumentationTest() {

    @Test
    fun `should greet`() {
        // @formatter:off

        Given {
            spec(of("greet"))
        }.

        When {
            val json = objectMapper.json {
                GreetingRequest {
                    name = "Max"
                }
            }

            body(json)
            contentType(MediaType.APPLICATION_JSON_VALUE)
            post("http://${props.host}:$port/api/v1/greetings/greet")
        }.

        Then {
            statusCode(HttpStatus.OK.value())
            body("value", equalTo("Hello, Max!"))
        }

        // @formatter:on
    }
}
