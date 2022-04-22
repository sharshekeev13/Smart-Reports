package ru.inai.kursach_2_0.activity.user.info

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.UserModel
import ru.inai.kursach_2_0.utils.SharedPreference
import java.lang.Exception

class UserInfoViewModel(_context : Context) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private val context = _context
    private val sharedPreference = SharedPreference(context)
    private val repo = Repository()
    private val id = sharedPreference.getId()
    private val userInfoList = MutableLiveData<UserModel>()
    private val sharedPreferences = SharedPreference(context)
    private val isNightModeOn = sharedPreferences.getNightMode()


    fun getUserInfoList() = userInfoList
    fun getNightMode() = isNightModeOn

    init {
        getUserById()
    }

    private fun getUserById(){
       try {
           viewModelScope.launch {
               val response = repo.getUserById(id.toString())
               userInfoList.postValue(response!!)
           }
       }catch (e:Exception){
           Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
       }
    }

    fun setTheme() {
            if(sharedPreferences.getNightMode()){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPreferences.putNightMode(false)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPreferences.putNightMode(true)
            }
    }


}