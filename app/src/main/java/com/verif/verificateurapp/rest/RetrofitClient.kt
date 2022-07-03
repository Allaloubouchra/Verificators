package com.verif.verificateurapp.rest

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val TAG = RetrofitClient::class.simpleName
    private val gson = GsonBuilder()
        .enableComplexMapKeySerialization()
        .setPrettyPrinting()
        .setLenient()
        .create()

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(loggingInterceptor)
      .addInterceptor(Interceptor { chain ->
            val request = if (apiKey != null) {
                chain.request()
                   .newBuilder()
                    .addHeader("Authorization", "Token $apiKey")
                  .method(chain.request().method, chain.request().body)
                   .build()
            } else {
              chain.request()
           }
           chain.proceed(request)
      })
        .build()

    val client: Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.2:8083/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()


}