package ru.inai.kursach_2_0.repo.models

import java.io.Serializable

data class ManagerToDoAllModel(
    val date: String? = null,
    val description: String? = null,
    val id: Int? = null,
    val status: Boolean? = null,
    val title: String? = null,
    val user: User? = null
) : Serializable