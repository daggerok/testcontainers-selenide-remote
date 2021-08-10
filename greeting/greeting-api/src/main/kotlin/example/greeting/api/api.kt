package example.greeting.api

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY

// tag::greeting-api[]

// Requests

@JsonInclude(NON_EMPTY)
data class GreetingRequest(
    val name: String = "",
)

// DTO responses

@JsonInclude(NON_EMPTY)
data class GreetingDTO(
    val value: String = "",
)

// end::greeting-api[]
