package com.example.lpuactivity.Retrofit_requests.api.sevice

import com.example.lpuactivity.models.Userinfo
import com.example.lpuactivity.models.Video
import com.example.lpuactivity.models.Video1

import retrofit2.Call
import retrofit2.http.*

interface Dservice {
    // get user info
    @GET("userinfo/{id}")
    @Headers("Authorization:eyJhbGciOiJIUzI1NiJ9.YXl4eHVzaDI.pIp830uYSgWsuOOXRAFCgwjTJr12xw-UehSA8bX6358", "x-foo: bar")
    fun getUser(@Path("id")id:String?): Call<Userinfo>

    // get all tasks

    @GET("tasks")
    @Headers("Authorization:eyJhbGciOiJIUzI1NiJ9.YXl4eHVzaDI.pIp830uYSgWsuOOXRAFCgwjTJr12xw-UehSA8bX6358", "x-foo: bar")
    fun getTask(): Call<List<Video>>


    @GET("tasks/{id}")
    @Headers("Authorization:eyJhbGciOiJIUzI1NiJ9.YXl4eHVzaDI.pIp830uYSgWsuOOXRAFCgwjTJr12xw-UehSA8bX6358", "x-foo: bar")
    fun getpost(@Path("id") id: String):Call<List<Video1>>


    @GET("tasks/acc/{id}")
    @Headers("Authorization:eyJhbGciOiJIUzI1NiJ9.YXl4eHVzaDI.pIp830uYSgWsuOOXRAFCgwjTJr12xw-UehSA8bX6358", "x-foo: bar")
    fun get_acc_post(@Path("id") id: String):Call<List<Video1>>

}