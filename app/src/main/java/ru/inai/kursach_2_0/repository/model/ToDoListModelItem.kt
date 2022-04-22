package ru.inai.kursach_2_0.repository.model

import java.io.Serializable

data class ToDoListModelItem(
    val discription: String? = null,
    val title: String? = null
) : Serializable