package com.example.retrofit.usingMVVM.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.usingMVVM.Repository.repo
import com.example.retrofit.usingMVVM.models.quotesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(private val MyRepo: repo) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            MyRepo.getQuotes()
        }
    }

    val quotes: LiveData<quotesData>
        get() = MyRepo.quotes


}