package ru.inai.kursach_2_0.activity.manager

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Cache
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.user.info.UserInfoActivity
import ru.inai.kursach_2_0.databinding.ActivityManagerBinding

class ActivityManager : AppCompatActivity() {

    private lateinit var binding : ActivityManagerBinding
    private lateinit var viewModel: ManagerViewModel
    private lateinit var viewPager: ManagerViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ManagerViewModel(applicationContext)
        viewPager = ManagerViewPager(this)
        binding.viewPagerManager.adapter = viewPager
        setTabLayout()
        setActionBar()
    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabLayoutManager,binding.viewPagerManager){tab,position ->
            when(position){
                0 -> tab.text = resources.getText(R.string.employee)
                1 -> tab.text = resources.getText(R.string.toDo)
                2 -> tab.text = resources.getText(R.string.directions)
                3 -> tab.text = resources.getText(R.string.area)
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
        val builder = AlertDialog.Builder(this@ActivityManager, R.style.CustomAlertDialog)
            .create()
        val view = LayoutInflater.from(this@ActivityManager).inflate(R.layout.custom_logout_dialog,null)
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
        accType.text = getString(R.string.manager)
        val logo : ImageView = findViewById(R.id.logo_toolbar)
        logo.setOnClickListener {
            startActivity(Intent(this, UserInfoActivity::class.java))
        }
    }
}