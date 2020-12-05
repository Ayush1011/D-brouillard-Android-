package com.example.lpuactivity.Retrofit_requests.api

import com.example.lpuactivity.models.defaultResponse
import retrofit2.Call
import retrofit2.http.*


interface Api {



  // update task after accepting
  @FormUrlEncoded
    @POST("tasks/{id}")
    fun updateTask(@Path("id") id: String,  @Field("Email") email: String?):Call<defaultResponse>


    @FormUrlEncoded
    // login user
    @POST("login")
    fun createUser(
        @Field("Email") email: String?,
        @Field("password") password: String?,

    ): Call<defaultResponse>



    @FormUrlEncoded
    @POST("tasks")
    @Headers("Authorization:eyJhbGciOiJIUzI1NiJ9.YXl4eHVzaDI.pIp830uYSgWsuOOXRAFCgwjTJr12xw-UehSA8bX6358", "x-foo: bar")
    fun postwork(@Field("Email")email: String?,
                 @Field("TaskTitle") TaskTitle: String?,
                 @Field("PostBy") PostBy: String?,
                 @Field("TaskDis") TaskDis: String?,
                 @Field("Price") Price: String?,
                 @Field("TaskPic") TaskPic: String?,

                 ):Call<defaultResponse>


    @DELETE("tasks/{id}")
    @Headers("Authorization:eyJhbGciOiJIUzI1NiJ9.YXl4eHVzaDI.pIp830uYSgWsuOOXRAFCgwjTJr12xw-UehSA8bX6358", "x-foo: bar")
    fun deletepost(@Path("id")Id:String?):Call<defaultResponse>

    @PUT("tasks/acc/{id}")
    @Headers("Authorization:eyJhbGciOiJIUzI1NiJ9.YXl4eHVzaDI.pIp830uYSgWsuOOXRAFCgwjTJr12xw-UehSA8bX6358", "x-foo: bar")
    fun rejectTask(@Path("id")Id:String?):Call<defaultResponse>






}