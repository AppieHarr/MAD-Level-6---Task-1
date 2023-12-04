package com.example.mad.ui.repository

import android.util.Log
import com.example.mad.data.api.Api
import com.example.mad.data.api.ApiService
import com.example.mad.data.api.util.Resource
import com.example.mad.data.model.Cat
import com.example.mad.data.model.Dog
import kotlinx.coroutines.withTimeout

class DogsRepository {
    private val dogApiService: ApiService = Api.dogsClient

    /**
     * suspend function that calls a suspend function from the numbersApi call
     * @return result wrapped in our own Resource sealed class
     */
    suspend fun getRandomDog(): Resource<Dog> {

        val response = try {
            withTimeout(5_000) {
                dogApiService.getRandomDog()
            }
        } catch(e: Exception) {
            Log.e("NumbersRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }

        return Resource.Success(response)
    }
}