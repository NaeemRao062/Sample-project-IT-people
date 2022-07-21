package com.itpeople.sample.mvvm.data.api

import com.itpeople.sample.mvvm.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getUserResults(@Query("page") page:Int, @Query("results") result:Int): Response<UserResponse>
}