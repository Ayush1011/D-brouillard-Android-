package com.example.lpuactivity.Retrofit_requests.api

import com.example.lpuactivity.models.defaultResponse
import retrofit2.Call
import retrofit2.http.*


interface Api {


    @FormUrlEncoded

  // update task after accepting
    @PUT("tasks/{id}")
    fun updateTask(@Path("id") id: String):Call<defaultResponse>


    @FormUrlEncoded
    // login user
    @POST("login")
    fun createUser(
        @Field("Email") email: String?,
        @Field("password") password: String?,

    ): Call<defaultResponse>
}