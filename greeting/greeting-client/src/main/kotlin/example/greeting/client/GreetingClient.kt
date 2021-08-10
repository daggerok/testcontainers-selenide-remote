package example.greeting.client

import example.greeting.api.GreetingDTO
import example.greeting.api.GreetingRequest
import feign.Headers
import feign.RequestLine

@Headers("Content-Type: application/json")
interface GreetingClient {

    @RequestLine("POST /api/v1/greetings/greet")
    fun greet(request: GreetingRequest): GreetingDTO
}
