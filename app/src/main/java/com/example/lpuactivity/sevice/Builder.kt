package com.example.lpuactivity.sevice

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Builder {
    private const val URL ="http://192.168.1.8:3000/"

    private val okHttp = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())



    val retrofit = builder.build()

    fun<T> buildService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }




}