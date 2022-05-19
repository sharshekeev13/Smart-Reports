package ru.inai.kursach_2_0.login


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.activity.director.ActivityDirector
import ru.inai.kursach_2_0.activity.employee.ActivityEmployee
import ru.inai.kursach_2_0.activity.manager.ActivityManager
import ru.inai.kursach_2_0.activity.marketing.ActivityMarketing
import ru.inai.kursach_2_0.databinding.ActivityLoginBinding
import ru.inai.kursach_2_0.repo.models.loginAuthModel
import ru.inai.kursach_2_0.utils.LocaleHelper
import ru.inai.kursach_2_0.utils.SharedPreference


class ActivityLogin : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var context: Context
    private lateinit var sp : SharedPreference


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
            sp = SharedPreference(applicationContext)
            viewModel = LoginViewModel(
                binding.usernameTextInputEdittext,
                applicationContext, binding.loginButton,binding.progressBarLoginActivity,this)
        ifWifiCheckForButton()
        when {
            sp.getLanguage()=="en" -> {
                setRadioButtonTextColor("en")
                binding.radioButtonEn.isChecked = true
            }
            sp.getLanguage()=="ru" -> {
                setRadioButtonTextColor("ru")
                binding.radioButtonRu.isChecked = true
            }
            sp.getLanguage() == "ky" -> {
                setRadioButtonTextColor("ky")
                binding.radioButtonKy.isChecked = true
            }
        }
            binding.themeSwitch.isChecked = viewModel.getNightMode()
            setLanguage(sp.getLanguage())
            setContentView(binding.root)
        }


    override fun onStart() {
        super.onStart()
        ifWifiCheckForButton()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        ifWifiCheckForButton()
        binding.refreshButtonLoginPage.setOnClickListener {
            ifWifiCheckForButton()
        }
        binding.loginButton.setOnClickListener {
            binding.progressBarLoginActivity.visibility = View.VISIBLE
            binding.loginButton.visibility = View.GONE
            val loginAuth = loginAuthModel(binding.usernameTextInputEdittext.text.toString(),binding.passwordTextInputEdittext.text.toString())
            val accountType = viewModel.startActivityFun(loginAuth)
            startActivity(accountType)
        }

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

        binding.themeSwitch.setOnCheckedChangeListener{ buttonView, _ ->
            if(buttonView.isChecked){
                Handler().postDelayed({
                    viewModel.setTheme()
                },1000)
            }else if(!buttonView.isChecked){
                Handler().postDelayed({
                    viewModel.setTheme()
                },1000)
            }
        }
    }

    private fun setLanguage(language : String){
        context = LocaleHelper.setLocate(this,language)
        binding.signInTextview.text = resources.getString(R.string.sign_in)
        binding.loginDescription.text = resources.getString(R.string.description_login_page)
        binding.loginButton.text = resources.getString(R.string.login)
        binding.usernameTextview.text = resources.getString(R.string.username)
        binding.passwordTextview.text = resources.getString(R.string.password)
        binding.passwordTextInputEdittext.hint = resources.getString(R.string.password)
        binding.usernameTextInputEdittext.hint = resources.getString(R.string.username)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setRadioButtonTextColor(language: String){
        when (language) {
            "en" -> {
                binding.radioButtonEn.setTextColor(getColor(R.color.whiteBackActionBar))
                binding.radioButtonRu.setTextColor(getColor(R.color.halo_blueText))
                binding.radioButtonKy.setTextColor(getColor(R.color.halo_blueText))

            }
            "ru" -> {
                binding.radioButtonRu.setTextColor(getColor(R.color.whiteBackActionBar))
                binding.radioButtonEn.setTextColor(getColor(R.color.halo_blueText))
                binding.radioButtonKy.setTextColor(getColor(R.color.halo_blueText))
            }
            "ky" -> {
                binding.radioButtonKy.setTextColor(getColor(R.color.whiteBackActionBar))
                binding.radioButtonRu.setTextColor(getColor(R.color.halo_blueText))
                binding.radioButtonEn.setTextColor(getColor(R.color.halo_blueText))
            }
        }
    }

    private fun ifWifiCheckForButton() {
        if(checkNetworkConnection(applicationContext)){
            binding.noWiFiConnTextView.visibility = View.GONE
            binding.noWiFiConn.visibility = View.GONE
            binding.loginBoxForChangeVisibility.visibility = View.VISIBLE
            binding.refreshButtonLoginPage.visibility = View.GONE
        }else{
            binding.noWiFiConnTextView.visibility = View.VISIBLE
            binding.noWiFiConn.visibility = View.VISIBLE
            binding.loginBoxForChangeVisibility.visibility = View.GONE
            binding.refreshButtonLoginPage.visibility = View.VISIBLE
        }
    }


    private fun checkNetworkConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun startActivity(role : String){
        var intent : Intent? = null
        when(role){
            "DIRECTOR" -> intent = Intent(this@ActivityLogin, ActivityDirector::class.java)
            "MARKETING" -> intent = Intent(this@ActivityLogin, ActivityMarketing::class.java)
            "MANAGER" -> intent = Intent(this@ActivityLogin, ActivityManager::class.java)
            "WORKER" -> intent = Intent(this@ActivityLogin, ActivityEmployee::class.java)
        }
        startActivity(intent)
    }

}