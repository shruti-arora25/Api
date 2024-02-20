package com.example.retrofit


import com.google.gson.annotations.SerializedName

data class dataclass(
    @SerializedName("data")
    var data: List<Data>,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)