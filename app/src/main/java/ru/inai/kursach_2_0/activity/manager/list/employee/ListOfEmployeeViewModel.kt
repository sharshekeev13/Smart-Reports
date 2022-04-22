package ru.inai.kursach_2_0.activity.manager.list.employee

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.ListOfEmployeeModel
import ru.inai.kursach_2_0.repository.api.RepositoryAPI
import ru.inai.kursach_2_0.repository.model.Post
import java.lang.Exception

class ListOfEmployeeViewModel(val context: Context) : ViewModel() {

    private val listOfEmployee = MutableLiveData<ArrayList<ListOfEmployeeModel>>()
    fun getListOfEmployee() = listOfEmployee
    private val repo = Repository()

    init {
        getListApi()
    }

    private fun getListApi() {
        try {
            viewModelScope.launch {
                val response = repo.getListOfEmployee()
                listOfEmployee.postValue(response!!)
            }
        }catch (e:Exception){
            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
        }
    }
}