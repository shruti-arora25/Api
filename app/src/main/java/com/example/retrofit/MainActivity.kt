package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {


    private lateinit var bind: ActivityMainBinding
    private lateinit var list: ArrayList<Data>
    private lateinit var list2: ArrayList<String>
    private lateinit var retro: Retrofit
    private lateinit var apiIntfc: ApiInterface

    lateinit var adapterC: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)


        list = ArrayList()
        list2 = ArrayList()

        adapterC = RecyclerAdapter(list2, this@MainActivity)
        bind.recycle.adapter = adapterC

        val layoutManagerC = LinearLayoutManager(this)
        bind.recycle.layoutManager = layoutManagerC

        retro = Retrofit.Builder().baseUrl("https://sibyl.zealstrat.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiIntfc = retro.create(ApiInterface::class.java)



        //using coroutines
        lifecycle.coroutineScope.launch {
            suspendFunc()
        }


     //   method()


    }

    private suspend fun suspendFunc() {
        val response = apiIntfc.getfunData()
        val dataResponse: dataclass? = response.body()

        val jsonObject = JSONObject(Gson().toJson(dataResponse))


        val data = jsonObject.getJSONArray("data")
        for (i in 0 until data.length()) {
            val Object = data.getJSONObject(i)

            list2.add(Object.getString("name"))

            adapterC = RecyclerAdapter(list2, this@MainActivity)
            adapterC.notifyDataSetChanged()



            bind.recycle.adapter = adapterC


        }
    }



    fun method() {



        val data: Call<dataclass> = apiIntfc.getData()


        data.enqueue(object : Callback<dataclass> {

            override fun onResponse(call: Call<dataclass>, response: Response<dataclass>) {


                if (response.isSuccessful) {
//                  val jsonObject = JSONObject(response.body()!!.string())
//                     jsonObject.getJSONObject("response")
//                    .getJSONObject("content")
//                    .getJSONObject("l").getJSONArray("onBoardingAccount")
//                     .length() != 0


                    val dataResponse: dataclass? = response.body()

                    val jsonObject = JSONObject(Gson().toJson(dataResponse))


                    val data = jsonObject.getJSONArray("data")
                    for (i in 0 until data.length()) {
                        val Object = data.getJSONObject(i)

                        list2.add(Object.getString("name"))


                        // var name=Data(Object.getString("name"))

//                        val o = Data(
//                            id = Object.getInt("id"),
//                            iso = Object.getString("iso"),
//                            name = Object.getString("name"),
//                            nicename = Object.getString("nicename"),
//                            iso3 = Object.getString("iso3"),
//                            numcode = Object.getInt("numcode"),
//                            phonecode = Object.getInt("phonecode")
//                        )
//                        list.add(o)
                    }

//                    val dataResponse: dataclass? = response.body()
//                    for (myData in dataResponse?.data!!) {
//                        list.add(myData)
//
//                    }

                    adapterC = RecyclerAdapter(list2, this@MainActivity)
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