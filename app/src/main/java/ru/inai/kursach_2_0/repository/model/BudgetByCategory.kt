package ru.inai.kursach_2_0.repository.model

import java.io.Serializable

data class BudgetByCategory(
    val category : String? = null,
    val sum : Int? = null
) : Serializable