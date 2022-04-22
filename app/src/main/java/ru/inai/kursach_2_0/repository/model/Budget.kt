package ru.inai.kursach_2_0.repository.model

import java.io.Serializable

data class Budget(
    val members: Int? = null,
    val socialmedia: String? = null,
    val budget : Int? = null,
    val icon : String? = null
) : Serializable