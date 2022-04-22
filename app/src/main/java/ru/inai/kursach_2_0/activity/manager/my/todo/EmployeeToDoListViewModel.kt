package ru.inai.kursach_2_0.activity.manager.my.todo

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.ToDoModel
import ru.inai.kursach_2_0.repository.api.RepositoryAPI
import ru.inai.kursach_2_0.repository.model.ToDoListModelItem
import ru.inai.kursach_2_0.utils.SharedPreference

class EmployeeToDoListViewModel(val context : Context) : ViewModel() {

    private val listOfToDo = MutableLiveData<ArrayList<ToDoModel>>()
    fun getToDoList() = listOfToDo
    private val repo = Repository()
    private val sharedPreference : SharedPreference = SharedPreference(context)
    private val id = sharedPreference.getId()

    private fun getToDoListFromRepo() {
       try {
           viewModelScope.launch {
               val response = repo.getToDoById(id)
               listOfToDo.postValue(response!!)
           }
       }catch (e:Exception){
           Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
       }
    }
}