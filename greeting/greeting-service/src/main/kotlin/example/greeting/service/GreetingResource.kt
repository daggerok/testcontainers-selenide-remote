package example.greeting.service

import example.greeting.api.GreetingRequest
import example.greeting.api.extensions.GreetingDTO
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingResource {

    @ResponseStatus(OK)
    @PostMapping("/api/v1/greetings/greet")
    fun greet(@RequestBody request: GreetingRequest) = request.name.run {
        val name = this.ifBlank { "anonymous" }
        GreetingDTO {
            value = "Hello, $name!"
        }
    }

    @ExceptionHandler(Throwable::class)
    fun errorHandler(e: Throwable) = when {
        else ->
            ResponseEntity.badRequest().body(
                mapOf("error" to (e.message ?: "undefined reason"))
            )
    }
}
