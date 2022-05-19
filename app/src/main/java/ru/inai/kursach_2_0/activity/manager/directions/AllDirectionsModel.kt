package ru.inai.kursach_2_0.activity.manager.directions

import ru.inai.kursach_2_0.repo.models.User
import ru.inai.kursach_2_0.repo.models.employee.todo.Employee
import ru.inai.kursach_2_0.repo.models.employee.todo.Manager
import java.io.Serializable

data class AllDirectionsModel(
    val id : Int? = null,
    val title : String? = null,
    val description : String? = null,
    val employeeObject : Employee? = null,
    val managerObject : Manager? = null,
    val date : String? = null,
    val status : Boolean? = null
) : Serializable
