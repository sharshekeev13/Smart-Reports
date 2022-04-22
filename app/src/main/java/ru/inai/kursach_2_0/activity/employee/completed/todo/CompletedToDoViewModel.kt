package ru.inai.kursach_2_0.activity.employee.completed.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repository.api.RepositoryAPI
import ru.inai.kursach_2_0.repository.model.CompletedToDo

class CompletedToDoViewModel : ViewModel() {

    private val liveListCompletedToDo = MutableLiveData<ArrayList<CompletedToDo>>()
    fun getListCompletedToDo() = liveListCompletedToDo
    private val repo = RepositoryAPI()

    init {
        getCompletedToDoAPI()
    }

    private fun getCompletedToDoAPI() {
        viewModelScope.launch {
            val response = repo.getCompletedToDoS()
            liveListCompletedToDo.postValue(response!!)
        }
    }
}