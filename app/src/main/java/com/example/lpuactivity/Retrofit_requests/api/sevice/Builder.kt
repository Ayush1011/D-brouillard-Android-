package com.example.lpuactivity.Retrofit_requests.api.sevice

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Builder {
    private const val URL ="https://protected-oasis-07406.herokuapp.com/"  //ip address for each call changed by ip adderess if on local host


    private val okHttp = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())



    val retrofit = builder.build()

    // to build json data
    fun<T> buildService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }




}