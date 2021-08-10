package example.testing.extensions

import com.fasterxml.jackson.databind.ObjectMapper

fun <T> ObjectMapper.json(function: () -> T): String =
    writerWithDefaultPrettyPrinter().writeValueAsString(function.invoke())
