package ru.inai.kursach_2_0.repository.model

data class User(
    val id: Int,
    val login: String,
    val name: String,
    val password: String,
    val salary: Int,
    val surname: String,
    val userRole: String
)