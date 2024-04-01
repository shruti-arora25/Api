package com.example.retrofit.usingMVVM.models


import com.google.gson.annotations.SerializedName

data class quotesData(
    @SerializedName("count")
    var count: Int,
    @SerializedName("lastItemIndex")
    var lastItemIndex: Int,
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("totalCount")
    var totalCount: Int,
    @SerializedName("totalPages")
    var totalPages: Int
)