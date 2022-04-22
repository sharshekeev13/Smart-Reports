package ru.inai.kursach_2_0.repo.models

import java.io.Serializable

data class UpdateUserInfo(
    val name: String? = null,
    val password: String? = null,
    val salary: Int? = null,
    val surname: String? = null
) : Serializable