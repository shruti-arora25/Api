package com.example.retrofit.usingMVVM

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.retrofit.R
import com.example.retrofit.databinding.ActivityMainVmBinding
import com.example.retrofit.usingMVVM.APIInterfaces.quoteesAPI
import com.example.retrofit.usingMVVM.APIInterfaces.retroHelper
import com.example.retrofit.usingMVVM.Repository.repo
import com.example.retrofit.usingMVVM.ViewModel.MyViewModel
import com.example.retrofit.usingMVVM.ViewModel.VMFActory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityVM : AppCompatActivity() {

    private lateinit var bind: ActivityMainVmBinding
    private lateinit var vm:MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityMainVmBinding.inflate(layoutInflater)
        setContentView(bind.root)


        val quoteService=retroHelper.getInstance().create(quoteesAPI::class.java)

        val repository= repo(quoteService)

        vm=ViewModelProvider(this,VMFActory(repository)).get(MyViewModel::class.java)
        vm.quotes.observe(this,{
            Log.d("TAG------------------>",it.results.toString())

        })






    }
}