package ru.inai.kursach_2_0.repository.model

import java.io.Serializable

data class CompletedToDo(
    val title : String? = null,
    val description : String? = null,
    val date : String? = null
) : Serializable
