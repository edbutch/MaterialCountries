package com.edbutch.materialcountries.data.db.Country

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CurrenciesItem(@SerializedName("symbol")
                          val symbol: String = "",
                          @SerializedName("code")
                          val code: String = "",
                          @SerializedName("name")
                          val name: String = ""): Serializable