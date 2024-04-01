package com.example.retrofit.Basic

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {



    @GET("country")
    fun getData():Call<dataclass>

    @GET("country")
    suspend fun getfunData():Response<dataclass>




//    @GET("country/{name}")
//    fun getData(@Path("name") name: String): Call<dataclass>
}