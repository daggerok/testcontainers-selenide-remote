package example.testing.extensions

import org.assertj.core.api.Assertions

inline infix fun <T> T?.notNull(function: () -> String?): T =
    this ?: Assertions.fail(
        if (function().isNullOrBlank()) "may not be null"
        else "${function()} may not be null"
    )

fun <T> T?.notNull(message: String? = "fail fast"): T =
    this notNull { message }
