package ru.inai.kursach_2_0.activity.employee

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import ru.inai.kursach_2_0.login.ActivityLogin

@SuppressLint("StaticFieldLeak")
class EmployeeViewModel(_context : Context) : ViewModel() {

    private val context = _context


    fun logout(){
        context.startActivity(Intent(context.applicationContext,ActivityLogin::class.java))
    }
}