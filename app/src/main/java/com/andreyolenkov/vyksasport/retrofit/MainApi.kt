package com.andreyolenkov.vyksasport.retrofit

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApi {
    @POST("api/Login")
    suspend fun auth(@Body authRequest: AuthRequest): User
}