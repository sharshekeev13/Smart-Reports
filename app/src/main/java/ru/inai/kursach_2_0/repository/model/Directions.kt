package ru.inai.kursach_2_0.repository.model

import java.io.Serializable

data class Directions(
    val firstName : String? = null,
    val secondName : String? = null,
    val title : String? = null,
    val description : String? = null
) : Serializable