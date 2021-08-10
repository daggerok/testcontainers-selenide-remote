package example.testing

import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

@DisplayName("Feign client test")
@SpringBootTest(webEnvironment = DEFINED_PORT)
abstract class FeignClientTest : IntegrationTest()
