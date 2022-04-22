package ru.inai.kursach_2_0.activity.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.inai.kursach_2_0.R
import ru.inai.kursach_2_0.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}