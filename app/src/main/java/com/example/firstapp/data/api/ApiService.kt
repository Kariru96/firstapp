package com.example.firstapp.data.api

import com.example.firstapp.data.model.Message
import com.example.firstapp.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("messages")
    suspend fun getMessages(): List<Message>

    @POST("messages")
    suspend fun postMessage(@Body message: Message): Message
}