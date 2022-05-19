package ru.inai.kursach_2_0.repo.api

import retrofit2.Response
import ru.inai.kursach_2_0.repo.models.*
import ru.inai.kursach_2_0.repo.models.employee.todo.EmployeeToDoModel

class ApiServices(private val apiInstance: ApiInterface) {

    suspend fun getListOfEmployeeFromApi() : Response<ArrayList<ListOfEmployeeModel>>{
        return apiInstance.getListOfEmployee()
    }

    suspend fun getDistrictsAll() : Response<ArrayList<DistrictsModel>>{
        return apiInstance.getDistrictAll()
    }

    suspend fun loginApi(login : loginAuthModel) : Response<UserModel>{
        return apiInstance.loginApi(login)
    }

    suspend fun getUserById(id : String) : Response<UserModel>{
        return apiInstance.getUserById(id)
    }

    suspend fun getSocialMediaAll() : Response<ArrayList<SocialMediaModel>>{
        return apiInstance.getAllSocialMedia()
    }

    suspend fun updateSocialMedia(id : String, body : SocialMediaUpdateModel) : Response<SocialMediaModel>{
        return apiInstance.editSocialMedia(id,body)
    }

    suspend fun getAllBudget() : Response<AllBudget>{
        return apiInstance.getAllBudget()
    }

    suspend fun updateUserInfo(id : String, updateUserBody : UpdateUserInfo) : Response<UserModel>{
        return apiInstance.updateUserInfo(id,updateUserBody)
    }

    suspend fun getManagerToDoAll() : Response<ArrayList<ManagerToDoAllModel>>{
        return apiInstance.managerToDoAll()
    }

    suspend fun createToDo(description : String,title : String,user : Int) : Response<ManagerToDoAllModel>{
        return apiInstance.createToDoManager(description,title,user)
    }

    suspend fun getToDoById(id:Int) : Response<ArrayList<ToDoModel>>{
        return apiInstance.getUserToDoById(id)
    }

    suspend fun createToDoForEmployee(description:String,employeeId:Int,managerId:Int,title:String) : Response<EmployeeToDoModel>{
        return apiInstance.createToDoForEmployee(description,employeeId,managerId,title)
    }

    suspend fun getAllToDoEmployee(): Response<ArrayList<EmployeeToDoModel>>{
        return apiInstance.getEmployeeToDoAll()
    }

    suspend fun deleteToDo(id : Int) : Response<Unit>{
        return apiInstance.deleteToDo(id)
    }
}
