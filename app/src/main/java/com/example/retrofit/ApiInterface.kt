package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {



    @GET("country")
    fun getData():Call<dataclass>
}