package example.greeting.client.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("greetings")
data class GreetingProperties(
    val host: String = "undefined",
    val port: Int = 80,
)/* {
    init {
        require(host.isNotBlank()) { "host may not be null or empty" }
        require(port != 80 && port != 443 && port <= 1024) { "port is not 80, 443, or less than or equals 1024" }
    }
}*/
