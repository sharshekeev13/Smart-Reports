package ru.inai.kursach_2_0.activity.director.budgetcategory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.AllBudget
import ru.inai.kursach_2_0.repository.api.RepositoryAPI
import ru.inai.kursach_2_0.repository.model.BudgetByCategory

class BudgetCategoryViewModel : ViewModel() {

    private val listBudgetByCategory = MutableLiveData<AllBudget>()
    fun getListBudget() = listBudgetByCategory
    private val repo = Repository()

    init {
        getBudgetByCategoryList()
    }

    private fun getBudgetByCategoryList() {
        viewModelScope.launch {
            val response = repo.getAllBudgets()
            listBudgetByCategory.postValue(response!!)
        }
    }
}