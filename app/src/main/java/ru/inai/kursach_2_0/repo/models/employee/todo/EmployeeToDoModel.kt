package ru.inai.kursach_2_0.repo.models.employee.todo

import java.io.Serializable

data class EmployeeToDoModel(
    val date: String? = null,
    val description: String? = null,
    val employee: Employee? = null,
    val id: Int? = null,
    val manager: Manager? = null,
    val status: Boolean? = null,
    val title: String? = null
) : Serializable