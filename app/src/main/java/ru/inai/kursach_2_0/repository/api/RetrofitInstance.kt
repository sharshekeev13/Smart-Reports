package ru.inai.kursach_2_0.repository.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val baseUrlJSON = "https://my-json-server.typicode.com"
    val instance : Retrofit = Retrofit.Builder()
            .baseUrl(baseUrlJSON)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    private val getInstance : RetrofitRequest by lazy {
        instance.create(RetrofitRequest::class.java)
        }

    val apiClient = ApiService(getInstance)
    }



