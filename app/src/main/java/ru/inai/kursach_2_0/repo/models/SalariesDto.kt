package ru.inai.kursach_2_0.repo.models

import java.io.Serializable

data class SalariesDto(
    val name: String?=null,
    val sum: Int?=null
) : Serializable