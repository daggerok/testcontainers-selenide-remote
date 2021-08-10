package example.testing

import org.junit.jupiter.api.DisplayName
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@DisplayName("Integration test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class IntegrationTest : UnitTest()
