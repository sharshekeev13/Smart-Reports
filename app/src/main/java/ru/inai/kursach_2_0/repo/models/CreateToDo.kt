package ru.inai.kursach_2_0.repo.models

import android.text.Editable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CreateToDo(
    val description: String? = null,
    val title: String? = null,
    val user: Int? = null
) : Serializable