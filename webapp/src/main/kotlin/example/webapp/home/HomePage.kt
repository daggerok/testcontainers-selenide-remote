package example.webapp.home

import example.greeting.client.GreetingClient
import example.greeting.client.extensions.greet
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomePage(private val greetingClient: GreetingClient) {

    @GetMapping("/")
    fun index(model: Model) = model.run {
        val (value) = greetingClient.greet { name = "Home" }
        addAttribute("message", value)
        "index"
    }
}
