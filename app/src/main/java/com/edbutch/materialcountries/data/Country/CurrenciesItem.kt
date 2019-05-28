package com.edbutch.materialcountries.data.Country

import com.google.gson.annotations.SerializedName

data class CurrenciesItem(@SerializedName("symbol")
                          val symbol: String = "",
                          @SerializedName("code")
                          val code: String = "",
                          @SerializedName("name")
                          val name: String = "")