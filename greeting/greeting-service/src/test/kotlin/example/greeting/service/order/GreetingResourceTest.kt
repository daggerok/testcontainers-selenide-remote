package example.greeting.service.order

import example.greeting.api.GreetingDTO
import example.greeting.api.extensions.GreetingRequest
import example.greeting.client.autoconfigure.GreetingProperties
import example.testing.IntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpStatus

@DisplayName("OrdersResource spring RestTemplate test")
@Suppress("SpringJavaInjectionPointsAutowiringInspection", "HttpUrlsUsage")
class GreetingResourceTest @Autowired constructor(
    val restTemplate: TestRestTemplate,
    @LocalServerPort val port: Int,
    val props: GreetingProperties,
) : IntegrationTest() {

    lateinit var baseUrl: String

    @BeforeEach
    fun setUp() {
        baseUrl = "http://${props.host}:$port"
    }

    @Test
    fun `should greet`() {
        // given
        val request = GreetingRequest { name = "Maksimko" }

        // when
        val response = restTemplate.exchange<GreetingDTO>("$baseUrl/api/v1/greetings/greet", POST, HttpEntity(request))

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

        // and
        val actual = response.body ?: fail("body may not be null")
        assertThat(actual.value).isEqualTo("Hello, Maksimko!")
    }
}
