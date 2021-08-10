package example.greeting.service.order

import example.greeting.client.GreetingClient
import example.greeting.client.extensions.greet
import example.testing.FeignClientTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@DisplayName("GreetingResource feign GreetingClient test")
class GreetingClientTest(@Autowired val greetingClient: GreetingClient) : FeignClientTest() {

    @Test
    fun `should greet`() {
        // when
        val response = greetingClient.greet { name = "Max" }

        // then
        assertThat(response.value).isEqualTo("Hello, Max!")
    }
}
