package example.webapp.greet

import example.greeting.client.GreetingClient
import example.greeting.client.extensions.greet
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/greet")
class GreetPage(private val greetingClient: GreetingClient) {

    @GetMapping(path = ["", "/"])
    fun index(model: Model) = model.run {
        val (value) = greetingClient.greet { name = "Greet" }
        addAttribute("message", value)
        "greet/index"
    }
}
