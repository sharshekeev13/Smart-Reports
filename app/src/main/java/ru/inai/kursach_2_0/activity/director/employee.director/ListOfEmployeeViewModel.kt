package ru.inai.kursach_2_0.activity.director.employee.director

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.ListOfEmployeeModel
import ru.inai.kursach_2_0.repo.models.UpdateUserInfo
import ru.inai.kursach_2_0.repository.api.RepositoryAPI
import ru.inai.kursach_2_0.repository.model.Post
import ru.inai.kursach_2_0.utils.SharedPreference

class ListOfEmployeeViewModel(_context : Context) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private val context = _context
    private val listOfEmployee = MutableLiveData<ArrayList<ListOfEmployeeModel>>()
    fun getListOfEmployee() = listOfEmployee
    private val repo = Repository()
    private val sp : SharedPreference = SharedPreference(context)


    init {
        getListEmployeeApi()
    }

    fun getListEmployeeApi() {
        viewModelScope.launch {
            val response = repo.getListOfEmployee()
            listOfEmployee.postValue(response!!)
        }
    }

    fun updateUser(id : String,body : UpdateUserInfo){
        viewModelScope.launch {
            val response = repo.updateUserInfo(id,body)
        }
    }
}