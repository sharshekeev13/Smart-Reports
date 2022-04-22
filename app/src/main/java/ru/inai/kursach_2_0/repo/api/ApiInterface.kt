package ru.inai.kursach_2_0.repo.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import ru.inai.kursach_2_0.repo.models.*
import ru.inai.kursach_2_0.repo.models.employee.todo.EmployeeToDoModel

interface ApiInterface {

    @GET("./users/all")
    suspend fun getListOfEmployee() : Response<ArrayList<ListOfEmployeeModel>>

    @GET("./district/all")
    suspend fun getDistrictAll() : Response<ArrayList<DistrictsModel>>

    @POST ("./users/login")
    suspend fun loginApi(
        @Body loginAuth : loginAuthModel
    ) : Response<UserModel>

    @GET("/users/{id}")
    suspend fun getUserById(
        @Path("id") id : String
    ) : Response<UserModel>

    @GET("./social/all")
    suspend fun getAllSocialMedia() : Response<ArrayList<SocialMediaModel>>

    @PUT("/social/{id}")
    suspend fun editSocialMedia(
        @Path("id") id : String,
        @Body socialMediaUpdate : SocialMediaUpdateModel
    ) : Response<SocialMediaModel>

    @GET("./users/get-social-money")
    suspend fun getAllBudget() : Response<AllBudget>

    @PUT("/users/update/{id}")
    suspend fun updateUserInfo(
        @Path("id") id : String,
        @Body updateUserInfo : UpdateUserInfo
    ) : Response<UserModel>

    @GET("./ManagerTodo/all")
    suspend fun managerToDoAll() : Response<ArrayList<ManagerToDoAllModel>>

    @FormUrlEncoded
    @POST("./ManagerTodo/create")
    suspend fun createToDoManager(
        @Field("description") description : String,
        @Field("title") title : String,
        @Field("user") user : Int
    ) : Response<ManagerToDoAllModel>

    @GET("/ManagerTodo/get-activetodo-manager/{user}")
    suspend fun getUserToDoById(
        @Path("user") id : Int
    ) : Response<ArrayList<ToDoModel>>

    @GET("./todo-employee/all")
    suspend fun getEmployeeToDoAll() : Response<ArrayList<EmployeeToDoModel>>

    @FormUrlEncoded
    @POST("./todo-employee/manager-create")
    suspend fun createToDoForEmployee(
        @Field("description") description : String,
        @Field("employeeId") employeeId : Int,
        @Field("managerId") managerId : Int,
        @Field("title") title : String
    ) : Response<EmployeeToDoModel>


}