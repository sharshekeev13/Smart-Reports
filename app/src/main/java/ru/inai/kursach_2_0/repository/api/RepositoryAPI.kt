package ru.inai.kursach_2_0.repository.api


import ru.inai.kursach_2_0.repository.api.RetrofitInstance
import ru.inai.kursach_2_0.repository.model.*

class RepositoryAPI() {

    suspend fun getListOfBudgetForMarketing() : ArrayList<Budget>? {
        val request = RetrofitInstance.apiClient.getListOfCategory()
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }
    suspend fun getEmployee(): ArrayList<Post>? {
        val request = RetrofitInstance.apiClient.getListOfEmployees()
        if(request.isSuccessful){
            return request.body()
        }
        return null
    }
    suspend fun getToDo(): ArrayList<ToDoListModelItem>? {
        val request = RetrofitInstance.apiClient.getToDoList()
        if(request.isSuccessful){
            return request.body()
        }
        return null
    }

    suspend fun getDistricts(): ArrayList<Districts>? {
        val request = RetrofitInstance.apiClient.getDistrict()
        if(request.isSuccessful){
            return  request.body()!!
        }
        return null
    }

    suspend fun getCompletedToDoS() : ArrayList<CompletedToDo>? {
        val request = RetrofitInstance.apiClient.getCompletedToDo()
        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getBudgetByCategoryAll() : ArrayList<BudgetByCategory>?{
        val request = RetrofitInstance.apiClient.getBudgetByCategory()
        if (request.isSuccessful){
            return request.body()
        }
        return null
    }
}