package ru.inai.kursach_2_0.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.director.ActivityDirector
import ru.inai.kursach_2_0.activity.employee.ActivityEmployee
import ru.inai.kursach_2_0.activity.marketing.ActivityMarketing
import ru.inai.kursach_2_0.repo.api.Repository
import ru.inai.kursach_2_0.repo.models.loginAuthModel
import ru.inai.kursach_2_0.utils.SharedPreference

@SuppressLint("StaticFieldLeak")
class LoginViewModel(
    _usernameEditText: EditText,
    _context: Context,
    _loginButton: AppCompatButton,
    _progressBarLoginActivity: ProgressBar,
    _activityLogin: ActivityLogin
) : ViewModel() {

    private val progressBar = _progressBarLoginActivity
    private val usernameEditText : EditText = _usernameEditText
    private val context : Context = _context
    private val loginButton = _loginButton
    private val repo = Repository()
    private val sharedPreferences = SharedPreference(context)
    private val isNightModeOn = sharedPreferences.getNightMode()
    private val activityLogin = _activityLogin


    init {
        setThemeInit()
    }

    fun getNightMode() = isNightModeOn


    fun checkLogin(login : loginAuthModel){
        viewModelScope.launch {
            val response = repo.loginFunction(login)
            if (response != null) {
                if (response.isSuccessful) {
                    sharedPreferences.putId(response.body()!!.id)
                    progressBar.visibility = View.GONE
                    startActivity(response.body()!!.userRole.toString())
                }
            }else{
                progressBar.visibility = View.GONE
                Toast.makeText(context,"ERROR!",Toast.LENGTH_LONG).show()
                incorrectLogin()
                loginButton.visibility = View.VISIBLE
            }
        }
    }

    private fun incorrectLogin(){
        usernameEditText.background = ContextCompat.getDrawable(context, R.drawable.login_textinput_background_incorrect)
        usernameEditText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat
            .getDrawable(context,R.drawable.custom_account_drawble_incorrect),
        null,null,null)
    }

    fun setTheme(){
        if(sharedPreferences.getNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPreferences.putNightMode(false)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPreferences.putNightMode(true)
        }
    }

    private fun setThemeInit(){
        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun startActivity(role : String){
        var intent : Intent? = null
        when(role){
            "DIRECTOR" -> intent = Intent(context,ActivityDirector::class.java)
            "MARKETING" -> intent = Intent(context,ActivityMarketing::class.java)
            "MANAGER" -> intent = Intent(context,ru.inai.kursach_2_0.activity.manager.ActivityManager::class.java)
            "WORKER" -> intent = Intent(context,ActivityEmployee::class.java)
        }
        context.startActivity(intent)
    }
}