package ru.inai.kursach_2_0.activity.manager.directions

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.ManagerToDoAllModel
import ru.inai.kursach_2_0.repo.models.ToDoAllModel
import ru.inai.kursach_2_0.repo.models.User
import ru.inai.kursach_2_0.repo.models.employee.todo.EmployeeToDoModel

class DirectionViewModel() : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private val liveListDirections = MutableLiveData<ArrayList<EmployeeToDoModel>>()
    fun getDirections() = liveListDirections
    private val repo = Repository()

    init {
        getAllToDoEmployee()
    }

    fun getAllToDoEmployee() {
        viewModelScope.launch {
            val response = repo.getAllToDoEmployee()
            liveListDirections.postValue(response!!)
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