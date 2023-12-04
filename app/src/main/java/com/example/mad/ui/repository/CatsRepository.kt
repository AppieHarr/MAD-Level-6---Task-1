package com.example.mad.ui.repository

import android.util.Log
import com.example.mad.data.api.Api
import com.example.mad.data.api.ApiService
import com.example.mad.data.api.util.Resource
import com.example.mad.data.model.Cat
import kotlinx.coroutines.withTimeout

class CatsRepository {
    private val catApiService: ApiService = Api.catsClient

    /**
     * suspend function that calls a suspend function from the numbersApi call
     * @return result wrapped in our own Resource sealed class
     */
    suspend fun getRandomCat(): Resource<Cat> {

        val response = try {
            withTimeout(5_000) {
                catApiService.getRandomCat()
            }
        } catch(e: Exception) {
            Log.e("NumbersRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }

        return Resource.Success(response)
    }
}