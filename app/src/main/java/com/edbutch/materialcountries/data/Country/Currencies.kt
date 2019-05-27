package com.edbutch.materialcountries.data.Country

import com.google.gson.annotations.SerializedName


data class Currencies (

    @SerializedName("code") val code : String,
    @SerializedName("name") val name : String,
    @SerializedName("symbol") val symbol : String
)