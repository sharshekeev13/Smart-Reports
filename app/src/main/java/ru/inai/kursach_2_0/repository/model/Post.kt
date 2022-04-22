package ru.inai.kursach_2_0.repository.model

import java.io.Serializable

data class Post(
    val firstName: String? = null,
    val position: String? = null,
    val salary: String? = null,
    val secondNAme: String? = null
) : Serializable
