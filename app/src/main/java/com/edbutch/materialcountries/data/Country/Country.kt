package com.edbutch.materialcountries.data.Country

import com.google.gson.annotations.SerializedName

data class Country(@SerializedName("area")
                   val area: Double = 0.0,
                   @SerializedName("nativeName")
                   val nativeName: String = "",
                   @SerializedName("capital")
                   val capital: String = "",
                   @SerializedName("demonym")
                   val demonym: String = "",
                   @SerializedName("flag")
                   val flag: String = "",
                   @SerializedName("alpha2Code")
                   val alpha2Code: String = "",
                   @SerializedName("languages")
                   val languages: List<LanguagesItem>?,
                   @SerializedName("borders")
                   val borders: List<String>?,
                   @SerializedName("subregion")
                   val subregion: String = "",
                   @SerializedName("callingCodes")
                   val callingCodes: List<String>?,
                   @SerializedName("regionalBlocs")
                   val regionalBlocs: List<RegionalBlocsItem>?,
                   @SerializedName("gini")
                   val gini: Double = 0.0,
                   @SerializedName("population")
                   val population: Int = 0,
                   @SerializedName("numericCode")
                   val numericCode: String = "",
                   @SerializedName("alpha3Code")
                   val alpha3Code: String = "",
                   @SerializedName("topLevelDomain")
                   val topLevelDomain: List<String>?,
                   @SerializedName("timezones")
                   val timezones: List<String>?,
                   @SerializedName("cioc")
                   val cioc: String = "",
                   @SerializedName("translations")
                   val translations: Translations,
                   @SerializedName("name")
                   val name: String = "",
                   @SerializedName("altSpellings")
                   val altSpellings: List<String>?,
                   @SerializedName("region")
                   val region: String = "",
                   @SerializedName("latlng")
                   val latlng: List<Double>?,
                   @SerializedName("currencies")
                   val currencies: List<CurrenciesItem>?)