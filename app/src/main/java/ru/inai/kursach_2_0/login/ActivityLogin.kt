package ru.inai.kursach_2_0.login


import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ru.inai.kursach_2_0.R
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
            if(sp.getLanguage()=="en"){
                setRadioButtonTextColor("en")
                binding.radioButtonEn.isChecked = true
            }else if(sp.getLanguage()=="ru"){
                setRadioButtonTextColor("ru")
                binding.radioButtonRu.isChecked = true
            }else if(sp.getLanguage() == "ky"){
                setRadioButtonTextColor("ky")
                binding.radioButtonKy.isChecked = true
            }
            binding.themeSwitch.isChecked = viewModel.getNightMode()
            setLanguage(sp.getLanguage())
            setContentView(binding.root)
        }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        binding.loginButton.setOnClickListener {
            binding.progressBarLoginActivity.visibility = View.VISIBLE
            binding.loginButton.visibility = View.GONE
            val loginAuth = loginAuthModel(binding.usernameTextInputEdittext.text.toString(),binding.passwordTextInputEdittext.text.toString())
            viewModel.checkLogin(loginAuth)
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