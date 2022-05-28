package com.example.fruitsapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://postmanmaster.herokuapp.com/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FruitApiService{
    @GET(value = "fruit")
    suspend fun getFruits(): List<Fruit>
}

object FruitApi{
    val retrofitService: FruitApiService by lazy{
        retrofit.create(FruitApiService::class.java)
    }
}