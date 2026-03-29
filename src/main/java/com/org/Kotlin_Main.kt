package com.org

fun main() {
    val answer = calculate {
        add(10.0)
        subtract(2.0)
        multiply(5.0)
        divide(4.0)
    }

    println(answer) // op = 10.0
}

class Calculator {
    var result: Double = 0.0
        private set

    fun add(value: Double) {
        result += value
    }

    fun subtract(value: Double) {
        result -= value
    }

    fun multiply(value: Double) {
        result *= value
    }

    fun divide(value: Double) {
        result /= value
    }
}

fun calculate(block: Calculator.() -> Unit): Double {
    val calculator = Calculator()
    calculator.block()
    return calculator.result
}