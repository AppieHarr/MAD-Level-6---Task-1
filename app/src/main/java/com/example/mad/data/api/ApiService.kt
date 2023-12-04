package com.example.mad.data.api

import com.example.mad.data.model.Cat
import com.example.mad.data.model.Dog
import retrofit2.http.GET

interface ApiService {
    @GET("/cat?json=true")
    suspend fun getRandomCat(): Cat

    @GET("/api/breeds/image/random")
    suspend fun getRandomDog(): Dog
}