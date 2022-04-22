package ru.inai.kursach_2_0.activity.director

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Cache
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.user.info.UserInfoActivity
import ru.inai.kursach_2_0.databinding.ActivityDirectorBinding


class ActivityDirector : AppCompatActivity() {

    private lateinit var binding : ActivityDirectorBinding
    private lateinit var viewPager : DirectorViewPager
    private lateinit var viewModel : DirectorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDirectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar()
        viewModel = DirectorViewModel(applicationContext)
        viewPager = DirectorViewPager(this)
        binding.viewPagerDirector.adapter = viewPager
        setTabLayout()
    }

    override fun onBackPressed() {
        alertDialog()
    }

    override fun onDestroy() {
        val cache : Cache = LruCache(applicationContext)
        cache.clear()
        super.onDestroy()
    }

    private fun alertDialog(){
        val builder = AlertDialog.Builder(this@ActivityDirector, R.style.CustomAlertDialog)
            .create()
        val view = LayoutInflater.from(this@ActivityDirector).inflate(R.layout.custom_logout_dialog,null)
        builder.setView(view)
        val yesButton : Button = view.findViewById(R.id.yes_button)
        yesButton.setOnClickListener {
            viewModel.logout()
        }
        val cancelButton : Button = view.findViewById(R.id.cancel_button_alert)
        cancelButton.setOnClickListener {
            builder.dismiss()
        }
        val logoutIconAlert : ImageView = view.findViewById(R.id.logout_icon_alert)
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/305/305703.png").into(logoutIconAlert)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }

    private fun setActionBar(){
        val typeOfAcc : TextView = findViewById(R.id.accType_TextView)
        typeOfAcc.text = getString(R.string.director)
        val logout : ImageView = findViewById(R.id.logout_toolbar)
        logout.setOnClickListener {
            alertDialog()
        }
        val logo : ImageView = findViewById(R.id.logo_toolbar)
        logo.setOnClickListener {
            startActivity(Intent(this,UserInfoActivity::class.java))
        }

    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabLayoutDirector,binding.viewPagerDirector){tab,position ->
            when(position){
                0 -> tab.text = resources.getString(R.string.area)
                1 -> tab.text = resources.getString(R.string.budget)
                2 -> tab.text = resources.getString(R.string.marketing)
                3 -> tab.text = resources.getString(R.string.employee)
            }
        }.attach()
    }
}