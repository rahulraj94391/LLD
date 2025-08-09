package com.org

import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class RegexTimeout {
    suspend fun matches(
        patternString: String,
        input: String,
        timeOut: Int = 5,
    ): Boolean? {
        return try {
            val pattern = Regex(patternString)
            val timeoutMillis = TimeUnit.SECONDS.toMillis(timeOut.toLong())
            withTimeout(timeoutMillis) {
                withContext(Dispatchers.Default) {
                    pattern.matches(input)
                }
            }
        } catch (e: TimeoutCancellationException) {
            println("Timeout inside matches(): ${e.message}")
            null
        }
    }
}

fun main() = runBlocking {
    val patternString = "^([a-zA-Z0-9])(([\\-]|[_]+?)?([a-zA-Z0-9]+?))*?(@){1}[a-z0-9]+?[.]{1}(([a-z]{2,3})|([a-z]{2,3}[.]{1}[a-z]{2,3}))"
    val userInput = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa!"

    val isMatch = RegexTimeout().matches(patternString, userInput, 1)

    when (isMatch) {
        true -> println("Match: true")
        false -> println("Match: false")
        null -> println("Regex evaluation failed or timed out")
    }
}
