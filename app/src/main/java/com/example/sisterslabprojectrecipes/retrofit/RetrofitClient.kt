package com.example.sisterslabprojectrecipes.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
companion object{
        fun getClient(baseUrl:String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//gson ile json verilerini parse etmek için
                .build()
        }
    }
}