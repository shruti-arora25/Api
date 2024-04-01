package com.example.retrofit.usingMVVM.APIInterfaces

import com.example.retrofit.usingMVVM.models.quotesData
import retrofit2.Response
import retrofit2.http.GET

interface quoteesAPI {


    @GET("quotes")
    suspend fun getContent():Response<quotesData>
}