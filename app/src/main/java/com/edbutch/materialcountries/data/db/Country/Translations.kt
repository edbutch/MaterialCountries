package com.edbutch.materialcountries.data.db.Country

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Translations(@SerializedName("br")
                        val br: String = "",
                        @SerializedName("de")
                        val de: String = "",
                        @SerializedName("pt")
                        val pt: String = "",
                        @SerializedName("ja")
                        val ja: String = "",
                        @SerializedName("hr")
                        val hr: String = "",
                        @SerializedName("it")
                        val it: String = "",
                        @SerializedName("fa")
                        val fa: String = "",
                        @SerializedName("fr")
                        val fr: String = "",
                        @SerializedName("es")
                        val es: String = "",
                        @SerializedName("nl")
                        val nl: String = ""): Serializable