package com.example.lpuactivity.Retrofit_requests.api.sevice

import com.example.lpuactivity.models.Userinfo
import com.example.lpuactivity.models.Video

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface Dservice {
    // get user info
    @GET("userinfo/{id}")
    @Headers("Authorization:eyJhbGciOiJIUzI1NiJ9.YXl4eHVzaDI.pIp830uYSgWsuOOXRAFCgwjTJr12xw-UehSA8bX6358", "x-foo: bar")
    fun getUser(@Path("id")id:String?): Call<Userinfo>

    // get all tasks

    @GET("tasks")
    @Headers("Authorization:eyJhbGciOiJIUzI1NiJ9.YXl4eHVzaDI.pIp830uYSgWsuOOXRAFCgwjTJr12xw-UehSA8bX6358", "x-foo: bar")
    fun getTask(): Call<List<Video>>

}