package ru.inai.kursach_2_0.activity.marketing

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Cache
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.user.info.UserInfoActivity
import ru.inai.kursach_2_0.databinding.ActivityMarketingBinding

class ActivityMarketing : AppCompatActivity() {

    private lateinit var binding : ActivityMarketingBinding
    private lateinit var viewPagerAdapter : ViewPagerAdapterMarketing
    private lateinit var viewModel : MarketingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = MarketingViewModel(applicationContext)
        viewPagerAdapter = ViewPagerAdapterMarketing(this)
        binding.viewPagerMarketing.adapter = viewPagerAdapter
        setActionBar()
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
        val builder = AlertDialog.Builder(this@ActivityMarketing, R.style.CustomAlertDialog)
            .create()
        val view = LayoutInflater.from(this@ActivityMarketing).inflate(R.layout.custom_logout_dialog,null)
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
        val window = builder.window
        val wlp : WindowManager.LayoutParams = window!!.attributes
        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        window.attributes = wlp
        builder.show()
    }



    private fun setActionBar(){
       val logoutToolBar : ImageView = findViewById(R.id.logout_toolbar)
        logoutToolBar.setOnClickListener {
            alertDialog()
        }
        val logo : ImageView = findViewById(R.id.logo_toolbar)
        logo.setOnClickListener {
            startActivity(Intent(this, UserInfoActivity::class.java))
        }
    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabLayoutMarketing,binding.viewPagerMarketing){tab,position ->
            if(position==0){
                tab.text = resources.getText(R.string.area)
            }else{
                tab.text = resources.getText(R.string.social_media)
            }
        }.attach()
    }
}