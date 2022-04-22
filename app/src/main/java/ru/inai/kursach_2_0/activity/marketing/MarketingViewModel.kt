package ru.inai.kursach_2_0.activity.marketing

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.login.ActivityLogin

@SuppressLint("StaticFieldLeak")
class MarketingViewModel(_context : Context) : ViewModel() {
    private val context = _context


    fun logout(){
        context.startActivity(Intent(context,ActivityLogin::class.java))
    }
}