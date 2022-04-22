package ru.inai.kursach_2_0.repo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
    private const val baseURL = "https://inaicoursework.herokuapp.com"
    private val instance : Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val getInstance : ApiInterface by lazy {
        instance.create(ApiInterface::class.java)
    }
    val apiClients = ApiServices(getInstance)
}