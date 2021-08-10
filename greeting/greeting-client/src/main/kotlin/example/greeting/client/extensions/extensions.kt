package example.greeting.client.extensions

import example.greeting.api.extensions.GreetingRequest
import example.greeting.api.extensions.GreetingRequestBuilder
import example.greeting.client.GreetingClient

/*
    val result = greetingClient.greet {
        name = "Max"
    }
*/

fun GreetingClient.greet(block: GreetingRequestBuilder.() -> Unit = {}) =
    greet(
        GreetingRequest {
            block(this)
        }
    )
