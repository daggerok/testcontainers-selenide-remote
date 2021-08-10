package example.greeting.api.extensions

import example.greeting.api.GreetingDTO
import example.greeting.api.GreetingRequest

/*
    val greeting = GreetingRequest {
        name = "Maksim"
    }
*/

data class GreetingRequestBuilder(var name: String = "")

fun GreetingRequestBuilder.build() = GreetingRequest(name)

fun GreetingRequest(block: GreetingRequestBuilder.() -> Unit = {}): GreetingRequest =
    GreetingRequestBuilder().run {
        block(this)
        build()
    }

/*
    val greeting = GreetingDTO {
        value = "Hello, anonymous!"
    }
*/

data class GreetingDtoBuilder(var value: String = "")

fun GreetingDtoBuilder.build() = GreetingDTO(value)

fun GreetingDTO(block: GreetingDtoBuilder.() -> Unit = {}): GreetingDTO =
    GreetingDtoBuilder().run {
        block(this)
        build()
    }
