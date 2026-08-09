package com.org

@JvmInline
value class EmployeeId(val value: String){

}

@JvmInline
value class CustomerId(val value: String)

fun printEmployee(id: EmployeeId) {
    println("Employee ID: ${id.value}")
}

fun printCustomer(id: CustomerId) {
    println("Customer ID: ${id.value}")
}

fun main() {

    val employeeId = EmployeeId("EMP-101")
    val customerId = CustomerId("CUS-501")

    // ✅ Correct types
    printEmployee(employeeId)
    printCustomer(customerId)

    // ❌ Uncomment this line and see the compilation error
    // printEmployee(customerId)

    // Both internally contain a String,
    // but Kotlin treats them as different types.
    println("EmployeeId value: ${employeeId.value}")
    println("CustomerId value: ${customerId.value}")
}