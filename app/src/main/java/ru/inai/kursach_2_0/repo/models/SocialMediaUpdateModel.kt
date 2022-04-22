package ru.inai.kursach_2_0.repo.models

import java.io.Serializable

data class SocialMediaUpdateModel(
    val money: Int? = null,
    val people: Int? = null,
    val social: String? = null
) : Serializable