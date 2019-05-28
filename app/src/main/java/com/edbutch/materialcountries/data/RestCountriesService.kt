package com.edbutch.materialcountries.data

import com.edbutch.materialcountries.data.Country.Country
import com.edbutch.materialcountries.data.Country.CountryResponse
import retrofit2.http.GET
import io.reactivex.Observable
import kotlin.collections.ArrayList

//https://restcountries.eu/rest/
//https://restcountries.eu/rest/v2/all


interface RestCountriesService {
    @GET("all")
    fun getAllCountries(): Observable<Array<Country>>

}