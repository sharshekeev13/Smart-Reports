package ru.inai.kursach_2_0.activity.manager.my.todo

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.manager.directions.AllDirectionsModel
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.ManagerToDoAllModel
import ru.inai.kursach_2_0.utils.SharedPreference

class EmployeeToDoListViewModel(_context : Context) : ViewModel() {

    private val listOfToDo = MutableLiveData<ArrayList<AllDirectionsModel>>()
    fun getToDoList() = listOfToDo
    private val repo = Repository()
    @SuppressLint("StaticFieldLeak")
    private val context = _context
    private val sharedPreference : SharedPreference = SharedPreference(context)
    private val id = sharedPreference.getId()
    fun getUserId() = id

    init {
        getToDoListFromRepo()
    }


    private fun getToDoListFromRepo() {
        viewModelScope.launch {
            val responseEmployee = repo.getAllToDoEmployee()
            responseEmployee!!.forEach {
                listOfToDo.postValue(arrayListOf(AllDirectionsModel(it.id,it.title,
                    it.description,it.employee,it.manager,it.date,it.status)))
            }
        }
    }

    private fun deleteToDoFromActive(list : ArrayList<AllDirectionsModel>){
        viewModelScope.launch {
            val response = repo.deleteToDo(list[0].id!!.toInt())
            if(response != null){
                FancyToast.makeText(context,context.getString(R.string.successSalary),
                    FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,false).show()
                getToDoListFromRepo()
            }else{
                FancyToast.makeText(context,context.getString(R.string.error),
                    FancyToast.LENGTH_SHORT, FancyToast.ERROR,false).show()
            }
        }
    }

    fun addToDoDoneToDo(list : ArrayList<AllDirectionsModel>){
        viewModelScope.launch {
            val response = repo.createToDo(list[0].description.toString(),
                list[0].title.toString(),list[0].employeeObject!!.id!!.toInt())
            if(response != null){
                deleteToDoFromActive(list)
            }else{
                FancyToast.makeText(context,context.getString(R.string.error),
                    FancyToast.LENGTH_SHORT, FancyToast.ERROR,false).show()
            }
        }
    }
}