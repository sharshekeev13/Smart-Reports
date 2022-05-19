package ru.inai.kursach_2_0.repo.models.employee.todo

import java.io.Serializable

data class Employee(
    val id: Int? = null,
    val login: String? = null,
    val name: String? = null,
    val password: String? = null,
    val salary: Int? = null,
    val surname: String? = null,
    val userRole: String? = null
) : Serializable