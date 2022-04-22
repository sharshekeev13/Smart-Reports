package ru.inai.kursach_2_0.repo.models

import java.io.Serializable

data class AllBudget(
    val marketingDto: MarketingDto? = null,
    val salariesDto: SalariesDto? = null
) : Serializable