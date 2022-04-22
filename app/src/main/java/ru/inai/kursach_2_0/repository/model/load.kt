package ru.inai.kursach_2_0.repository.model

import java.io.Serializable

data class load(
    val budget: List<Budget>? = null,
    val posts: List<Post>? = null,
    val profile: Profile? = null
) : Serializable