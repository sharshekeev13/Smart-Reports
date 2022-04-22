package ru.inai.kursach_2_0.activity.marketing.area

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.DistrictsModel

class AreaViewModel : ViewModel() {

    private val areaList = MutableLiveData<ArrayList<DistrictsModel>>()
    fun getAreaList() = areaList
    private val repo = Repository()

    init {
        getAreaApi()
    }

    private fun getAreaApi() {
        viewModelScope.launch {
            val response = repo.getDistrictsAll()
            areaList.postValue(response!!)
        }
    }
}