package ru.inai.kursach_2_0.activity.manager.directions

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.employee.todo.Employee

class DirectionViewModel() : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private val liveListDirections = MutableLiveData<ArrayList<AllDirectionsModel>>()
    fun getDirections() = liveListDirections
    private val repo = Repository()

    init {
        getAllToDoEmployee()
    }

    fun getAllToDoEmployee() {
        viewModelScope.launch {
            val ar = arrayListOf<AllDirectionsModel>()
            val responseEmployee = repo.getAllToDoEmployee()
            responseEmployee!!.forEach {
                ar.add(AllDirectionsModel(it.id,it.title,it.description,it.employee,it.manager,it.date,it.status))
            }
            val responseManager = repo.getAllManagerToDo()
            responseManager!!.forEach {
                val userToEmployee = Employee(it.user!!.id,it.user.login,
                    it.user.name,it.user.password,it.user.salary,it.user.surname,it.user.userRole)
                ar.add(AllDirectionsModel(it.id,it.title,it.description,userToEmployee,null,it.date,it.status))
            }
            liveListDirections.postValue(ar)
        }
    }

    fun addToDoForEmployee(
            description: String,
            employeeId: Int,
            managerId: Int,
            title: String
        ) {
        viewModelScope.launch {
                repo.createToDoForEmployee(description, employeeId, managerId, title)
            }
        }
}