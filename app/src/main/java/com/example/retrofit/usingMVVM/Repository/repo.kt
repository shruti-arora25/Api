package com.example.retrofit.usingMVVM.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit.usingMVVM.APIInterfaces.quoteesAPI
import com.example.retrofit.usingMVVM.models.quotesData

class repo(private val quoteService: quoteesAPI) {

    private val quotesLiveData= MutableLiveData<quotesData>()

    val quotes: LiveData<quotesData>
        get() = quotesLiveData


    suspend fun getQuotes() {
        val result = quoteService.getContent()
        if (result.body() != null) {
            quotesLiveData.postValue(result.body())


        }
    }


}