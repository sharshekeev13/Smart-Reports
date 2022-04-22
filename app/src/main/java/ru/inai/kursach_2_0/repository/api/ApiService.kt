package ru.inai.kursach_2_0.repository.api

import retrofit2.Response
import ru.inai.kursach_2_0.repository.api.RetrofitRequest
import ru.inai.kursach_2_0.repository.model.*

class ApiService (private val apiInterface: RetrofitRequest) {

    suspend fun getListOfCategory() : Response<ArrayList<Budget>> {
        return apiInterface.getBudgetCategoryMarketing()
    }
    suspend fun getListOfEmployees(): Response<ArrayList<Post>> {
        return apiInterface.getListOfEmployee()
    }
    suspend fun getToDoList(): Response<ArrayList<ToDoListModelItem>> {
        return apiInterface.getToDoList()
    }
    suspend fun getDistrict(): Response<ArrayList<Districts>> {
        return apiInterface.getDistricts()
    }
    suspend fun getCompletedToDo() : Response<ArrayList<CompletedToDo>>{
        return apiInterface.getCompletedToDo()
    }
    suspend fun getBudgetByCategory() : Response<ArrayList<BudgetByCategory>>{
        return apiInterface.getBudgetByCategory()
    }
}