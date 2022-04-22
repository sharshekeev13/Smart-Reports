package ru.inai.kursach_2_0.activity.user.info


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.director.ActivityDirector
import ru.inai.kursach_2_0.activity.employee.ActivityEmployee
import ru.inai.kursach_2_0.activity.manager.ActivityManager
import ru.inai.kursach_2_0.activity.marketing.ActivityMarketing
import ru.inai.kursach_2_0.databinding.ActivityUserInfoBinding
import ru.inai.kursach_2_0.utils.LocaleHelper
import ru.inai.kursach_2_0.utils.SharedPreference

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUserInfoBinding
    private lateinit var viewModel: UserInfoViewModel
    private lateinit var sp : SharedPreference
    private lateinit var context : Context
    private var role : String? = null
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        sp = SharedPreference(applicationContext)
        viewModel = UserInfoViewModel(applicationContext)
        viewModel.getUserInfoList().observe(this) {
            binding.userInfoName.text = it.name + " " + it.surname
            binding.mySalaryNumTv.text = it.salary.toString()
            binding.userInfoRole.text = it.userRole.toString()
            role = it.userRole
        }
        if (sp.getLanguage() == "en") {
            setRadioButtonTextColor("en")
            binding.radioButtonEn.isChecked = true
        } else if (sp.getLanguage() == "ru") {
            setRadioButtonTextColor("ru")
            binding.radioButtonRu.isChecked = true
        } else if (sp.getLanguage() == "ky") {
            setRadioButtonTextColor("ky")
            binding.radioButtonKy.isChecked = true
        }
        binding.themeSwitchUserInfo.isChecked = viewModel.getNightMode()
        setLanguage(sp.getLanguage())
        Picasso.get()
            .load("https://img.asmedia.epimg.net/resizer/pMlrmRFj_eU4ee_G1KrUOl0p4bM=/644x362/cloudfront-eu-central-1.images.arcpublishing.com/diarioas/V3IXQ3JZMZH4BKHGY6KRAQ5I7A.jpeg")
            .into(binding.backgroundUserInfo)
        setContentView(binding.root)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        binding.radioButtonEn.setOnClickListener {
            setRadioButtonTextColor("en")
            sp.putLanguage("en")
            setLanguage("en")
        }
        binding.radioButtonRu.setOnClickListener {
            setRadioButtonTextColor("ru")
            sp.putLanguage("ru")
            setLanguage("ru")
        }
        binding.radioButtonKy.setOnClickListener {
            setRadioButtonTextColor("ky")
            sp.putLanguage("ky")
            setLanguage("ky")
        }
        binding.themeSwitchUserInfo.setOnCheckedChangeListener { buttonView, _ ->
            if (buttonView.isChecked) {
                Handler().postDelayed({
                    viewModel.setTheme()
                }, 1000)
            } else if (!buttonView.isChecked) {
                Handler().postDelayed({
                    viewModel.setTheme()
                }, 1000)
            }
        }
        binding.backButton.setOnClickListener {
            backFunction(role)
        }
    }

    override fun onBackPressed() {
        backFunction(role)
    }

    private fun backFunction(userRole: String?) {
        when(userRole){
            "MARKETING" -> startActivity(Intent(this@UserInfoActivity,ActivityMarketing::class.java))
            "DIRECTOR" -> startActivity(Intent(this@UserInfoActivity,ActivityDirector::class.java))
            "WORKER" -> startActivity(Intent(this@UserInfoActivity,ActivityEmployee::class.java))
            "MANAGER" -> startActivity(Intent(this@UserInfoActivity,ActivityManager::class.java))
        }
    }

    private fun setLanguage(language : String){
        context = LocaleHelper.setLocate(this,language)
        binding.settingsTextView.text = resources.getString(R.string.settings)
        binding.userInfoInformationTV.text = resources.getString(R.string.profile_settings)
        binding.userInfoMySalaryTV.text = resources.getString(R.string.my_salary)
        binding.switchThemeUserInfo.text = resources.getString(R.string.switch_theme)
        binding.changePassword.text =  resources.getString(R.string.change_password)
        binding.changeLanguage.text = resources.getString(R.string.change_language)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun setRadioButtonTextColor(language: String){
        if(language == "en"){
            binding.radioButtonEn.setTextColor(getColor(R.color.whiteBackActionBar))
            binding.radioButtonRu.setTextColor(getColor(R.color.halo_blueText))
            binding.radioButtonKy.setTextColor(getColor(R.color.halo_blueText))

        }else if(language == "ru"){
            binding.radioButtonRu.setTextColor(getColor(R.color.whiteBackActionBar))
            binding.radioButtonEn.setTextColor(getColor(R.color.halo_blueText))
            binding.radioButtonKy.setTextColor(getColor(R.color.halo_blueText))
        }else if(language == "ky"){
            binding.radioButtonKy.setTextColor(getColor(R.color.whiteBackActionBar))
            binding.radioButtonRu.setTextColor(getColor(R.color.halo_blueText))
            binding.radioButtonEn.setTextColor(getColor(R.color.halo_blueText))
        }
    }


}