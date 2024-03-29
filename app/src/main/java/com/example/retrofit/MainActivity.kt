package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private lateinit var bind: ActivityMainBinding
    private lateinit var list: ArrayList<Data>
    lateinit var adapterC: RecyclerAdapter
    private lateinit var retro: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bind.root)
        Log.d("Tag", "Country")


        list = ArrayList()

        adapterC = RecyclerAdapter(list, this@MainActivity)
        bind.recycle.adapter = adapterC

        val layoutManagerC = LinearLayoutManager(this)
        bind.recycle.layoutManager = layoutManagerC





        retro = Retrofit.Builder().baseUrl("https://sibyl.zealstrat.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)


        lifecycleScope.launch {
            suspendfunc()
        }


        // method()


    }

    private suspend fun suspendfunc() {
        var response =
            retro.getfunData().body()


        for (myData in response?.data!!) {
            list.add(myData)

        }


        adapterC = RecyclerAdapter(list, this@MainActivity)
        adapterC.notifyDataSetChanged()



        bind.recycle.adapter = adapterC


    }


    fun method() {


        val data: Call<dataclass> = retro.getData()

        data.enqueue(object : Callback<dataclass> {

            override fun onResponse(call: Call<dataclass>, response: Response<dataclass>) {


                if (response.isSuccessful) {

                    val dataResponse: dataclass? = response.body()


                    for (myData in dataResponse?.data!!) {
                        list.add(myData)

                    }


                    adapterC = RecyclerAdapter(list, this@MainActivity)
                    adapterC.notifyDataSetChanged()



                    bind.recycle.adapter = adapterC


                }

            }

            override fun onFailure(call: Call<dataclass>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error found", Toast.LENGTH_SHORT).show()
            }


        })


    }
}