package com.edbutch.materialcountries.data.Country

import com.google.gson.annotations.SerializedName

data class RegionalBlocsItem(@SerializedName("acronym")
                             val acronym: String = "",
                             @SerializedName("name")
                             val name: String = "")