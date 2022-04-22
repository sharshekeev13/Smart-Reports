package ru.inai.kursach_2_0.repo.api

import retrofit2.Response
import ru.inai.kursach_2_0.repo.models.*
import ru.inai.kursach_2_0.repo.models.employee.todo.EmployeeToDoModel
import java.lang.Exception

class Repository {

    suspend fun getListOfEmployee():ArrayList<ListOfEmployeeModel>?{
        val request = ApiInstance.apiClients.getListOfEmployeeFromApi()
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getDistrictsAll():ArrayList<DistrictsModel>?{
        val request = ApiInstance.apiClients.getDistrictsAll()
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun loginFunction(login : loginAuthModel):Response<UserModel>?{
        try {
            val request = ApiInstance.apiClients.loginApi(login)
            if(request.isSuccessful){
                return request
            }
        }catch (e:Exception){
            return null
        }
        return null
    }

    suspend fun getUserById(id : String) : UserModel?{
        val request = ApiInstance.apiClients.getUserById(id)
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getAllSocialMedia():ArrayList<SocialMediaModel>?{
        val request = ApiInstance.apiClients.getSocialMediaAll()
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun updateSocialMedia(id : String,body : SocialMediaUpdateModel) : SocialMediaModel?{
        val request = ApiInstance.apiClients.updateSocialMedia(id,body)
        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getAllBudgets() : AllBudget?{
        val request = ApiInstance.apiClients.getAllBudget()
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun updateUserInfo(id : String, updateUserBody : UpdateUserInfo) : UserModel?{
        val request = ApiInstance.apiClients.updateUserInfo(id,updateUserBody)
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getAllManagerToDo() : ArrayList<ManagerToDoAllModel>?{
        val request = ApiInstance.apiClients.getManagerToDoAll()
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun createToDo(description : String,title : String,user : Int) : ManagerToDoAllModel?{
        val request = ApiInstance.apiClients.createToDo(description,title,user)
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getToDoById(id:Int) : ArrayList<ToDoModel>?{
        val request = ApiInstance.apiClients.getToDoById(id)
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun createToDoForEmployee(description:String,employeeId:Int,managerId:Int,title:String) : EmployeeToDoModel?{
        val request = ApiInstance.apiClients.createToDoForEmployee(description,employeeId,managerId,title)
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getAllToDoEmployee():ArrayList<EmployeeToDoModel>?{
        val request = ApiInstance.apiClients.getAllToDoEmployee()
        if(request.isSuccessful){
            return request.body()!!
        }
        return null
    }
}