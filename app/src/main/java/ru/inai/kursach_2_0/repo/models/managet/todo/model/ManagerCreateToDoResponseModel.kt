package ru.inai.kursach_2_0.repo.models.managet.todo.model

data class ManagerCreateToDoResponseModel(
    val date: String,
    val description: String,
    val id: Int,
    val status: Boolean,
    val title: String,
    val user: User
)