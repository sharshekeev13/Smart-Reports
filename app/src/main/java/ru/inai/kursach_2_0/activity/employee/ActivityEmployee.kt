package ru.inai.kursach_2_0.activity.employee

import android.app.AlertDialog
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Cache
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.user.info.UserInfoActivity
import ru.inai.kursach_2_0.databinding.ActivityEmployeeBinding

class ActivityEmployee : AppCompatActivity() {

    private lateinit var viewPager : EmployeeViewPager
    private lateinit var binding : ActivityEmployeeBinding
    private lateinit var viewModel : EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = EmployeeViewModel(applicationContext)
        viewPager = EmployeeViewPager(this)
        binding.viewPagerEmployee.adapter = viewPager
        setTabLayout()
        setActionBar()
    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabLayoutEmployee,binding.viewPagerEmployee){tab,position ->
            when(position){
                0 -> tab.text = resources.getText(R.string.toDo)
                1 -> tab.text = resources.getText(R.string.completed)
            }
        }.attach()
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
        val builder = AlertDialog.Builder(this@ActivityEmployee, R.style.CustomAlertDialog)
            .create()
        val view = LayoutInflater.from(this@ActivityEmployee).inflate(R.layout.custom_logout_dialog,null)
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
        val logoutToolBar : ImageView = findViewById(R.id.logout_toolbar)
        logoutToolBar.setOnClickListener {
            alertDialog()
        }
        val accType : TextView = findViewById(R.id.accType_TextView)
        accType.text = getString(R.string.employee)
        val logo : ImageView = findViewById(R.id.logo_toolbar)
        logo.setOnClickListener {
            startActivity(Intent(this, UserInfoActivity::class.java))
        }
    }
}