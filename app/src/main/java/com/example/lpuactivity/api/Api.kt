package com.example.lpuactivity.api

import com.example.lpuactivity.models.defaultResponse
import retrofit2.Call
import retrofit2.http.*


interface Api {


    @FormUrlEncoded

    @GET("user/{id}")
    fun getuser(@Path("id") id: String):Call<defaultResponse>

    @PUT("tasks/{id}")
    fun updateTask(@Path("id") id: String):Call<defaultResponse>


    @FormUrlEncoded
    @POST("login")
    fun createUser(
        @Field("Email") email: String?,
        @Field("password") password: String?,

    ): Call<defaultResponse>
}