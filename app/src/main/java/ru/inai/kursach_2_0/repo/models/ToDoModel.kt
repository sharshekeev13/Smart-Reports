package ru.inai.kursach_2_0.repo.models

import java.io.Serializable

data class ToDoModel(
    val description: String? = null,
    val title: String? = null,
    val user: Int? = null
) : Serializable