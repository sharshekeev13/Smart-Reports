package ru.inai.kursach_2_0.repository.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import ru.inai.kursach_2_0.repository.model.*

interface RetrofitRequest {
    @Headers("Cache-Control: max-age=640000")
    @GET("./sharshekeev13/demoJSON/posts")
    suspend fun getListOfEmployee() : Response<ArrayList<Post>>

    @Headers("Cache-Control: max-age=640000")
    @GET("./sharshekeev13/demoJSON/budget")
    suspend fun getBudgetCategoryMarketing() : Response<ArrayList<Budget>>

    @Headers("Cache-Control: max-age=640000")
    @GET("./sharshekeev13/demoJSON/todo")
    suspend fun getToDoList() : Response<ArrayList<ToDoListModelItem>>

    @Headers("Cache-Control: max-age=640000")
    @GET("./sharshekeev13/demoJSON/Districts")
    suspend fun getDistricts() : Response<ArrayList<Districts>>

    @Headers("Cache-Control: max-age=640000")
    @GET("./sharshekeev13/demoJSON2/completedToDo")
    suspend fun getCompletedToDo() : Response<ArrayList<CompletedToDo>>

    @Headers("Cache-Control: max-age=640000")
    @GET("./sharshekeev13/demoJSON2/budgetCategory")
    suspend fun getBudgetByCategory() : Response<ArrayList<BudgetByCategory>>

}