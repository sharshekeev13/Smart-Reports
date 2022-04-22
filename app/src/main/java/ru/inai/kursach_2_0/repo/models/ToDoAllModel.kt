package ru.inai.kursach_2_0.repo.models

import ru.inai.kursach_2_0.repo.models.employee.todo.EmployeeToDoModel
import java.io.Serializable

data class ToDoAllModel(
    val ManagerToDo : ManagerToDoAllModel? = null,
    val EmployeeToDo: EmployeeToDoModel?=null
) : Serializable