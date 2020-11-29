package com.example.lpuactivity.api
import com.example.lpuactivity.sevice.Builder
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val Base_Url="http://192.168.1.8:3000/"

    private val okHttpClient=OkHttpClient.Builder()
        .addInterceptor { chain->
            val original = chain.request()
           val requestBuilder =original.newBuilder()
               .addHeader("Authorization","eyJhbGciOiJIUzI1NiJ9.YXl4eHVzaDI.pIp830uYSgWsuOOXRAFCgwjTJr12xw-UehSA8bX6358")
               .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()
    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }

    fun<T> buildService(serviceType:Class<T>):T{
        return Builder.retrofit.create(serviceType)
    }



}