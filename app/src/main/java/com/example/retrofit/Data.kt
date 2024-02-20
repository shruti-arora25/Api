package com.example.retrofit


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    var id: Int,
    @SerializedName("iso")
    var iso: String,
    @SerializedName("iso3")
    var iso3: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("nicename")
    var nicename: String,
    @SerializedName("numcode")
    var numcode: Int,
    @SerializedName("phonecode")
    var phonecode: Int
)