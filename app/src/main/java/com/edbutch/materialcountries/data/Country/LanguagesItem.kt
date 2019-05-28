package com.edbutch.materialcountries.data.Country

import com.google.gson.annotations.SerializedName

data class LanguagesItem(@SerializedName("nativeName")
                         val nativeName: String = "",
                         @SerializedName("iso639_2")
                         val iso2: String = "",
                         @SerializedName("name")
                         val name: String = "",
                         @SerializedName("iso639_1")
                         val iso1: String = "")