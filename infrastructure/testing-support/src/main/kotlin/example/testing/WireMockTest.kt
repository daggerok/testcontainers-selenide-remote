package example.testing

import org.junit.jupiter.api.DisplayName
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock

/*

Usage:

    @SpringBootApplication
    internal class MyTestApp

    @ContextConfiguration(classes = [MyTestApp::class])
    class MyTest : WireMockTest() {
        // ...
    }

*/

@DisplayName("WireMock test")
@AutoConfigureWireMock(port = 8765)
abstract class WireMockTest : IntegrationTest()
