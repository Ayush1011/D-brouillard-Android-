package com.example.lpuactivity.Retrofit_requests.api
import com.example.lpuactivity.Retrofit_requests.api.sevice.Builder
import com.example.lpuactivity.util.access
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val Base_Url="https://protected-oasis-07406.herokuapp.com/"  // ip address for local host

    private val okHttpClient=OkHttpClient.Builder()
        .addInterceptor { chain->
            val original = chain.request()
           val requestBuilder =original.newBuilder()
               .addHeader("Authorization", access!!)
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