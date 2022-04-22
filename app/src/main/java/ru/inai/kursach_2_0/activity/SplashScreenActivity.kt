package ru.inai.kursach_2_0.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.login.ActivityLogin
import ru.inai.kursach_2_0.login.LoginViewModel
import ru.inai.kursach_2_0.utils.SharedPreference

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var handler : Handler
    private lateinit var viewModelLogin : LoginViewModel
    private lateinit var sp : SharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = Handler()
        sp = SharedPreference(applicationContext)
        val isNightModeOn = sp.getNightMode()
        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        setContentView(R.layout.activity_splash_screen)
        handler.postDelayed({
            startActivity(Intent(this,ActivityLogin::class.java))
            finish()
        },1000)
    }
}