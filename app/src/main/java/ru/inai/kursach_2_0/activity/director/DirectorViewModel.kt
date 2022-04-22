package ru.inai.kursach_2_0.activity.director

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import ru.inai.kursach_2_0.login.ActivityLogin

class DirectorViewModel(_context : Context) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private val context = _context

    fun logout() {
        context.startActivity(Intent(context.applicationContext,ActivityLogin::class.java))
    }
}