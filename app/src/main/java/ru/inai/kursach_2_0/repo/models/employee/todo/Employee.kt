package ru.inai.kursach_2_0.repo.models.employee.todo

data class Employee(
    val id: Int,
    val login: String,
    val name: String,
    val password: String,
    val salary: Int,
    val surname: String,
    val userRole: String
)