package ru.inai.kursach_2_0.activity.employee.completed.todo

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.ManagerToDoAllModel
import ru.inai.kursach_2_0.repository.api.RepositoryAPI
import ru.inai.kursach_2_0.repository.model.CompletedToDo
import ru.inai.kursach_2_0.utils.SharedPreference

@SuppressLint("StaticFieldLeak")
class CompletedToDoViewModel(_context : Context) : ViewModel() {

    private val context = _context
    private val liveListCompletedToDo = MutableLiveData<ArrayList<ManagerToDoAllModel>>()
    fun getListCompletedToDo() = liveListCompletedToDo
    private val repo = Repository()
    private val sharedPreference : SharedPreference = SharedPreference(context)
    private val id = sharedPreference.getId()
    fun getUserId() = id

    init {
        getCompletedToDoAPI()
    }

    private fun getCompletedToDoAPI() {
        viewModelScope.launch {
            val response = repo.getAllManagerToDo()
            liveListCompletedToDo.postValue(response!!)
        }
    }
}