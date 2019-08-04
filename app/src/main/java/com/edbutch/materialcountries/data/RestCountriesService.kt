package com.edbutch.materialcountries.data

import com.edbutch.materialcountries.data.Country.Country
import com.edbutch.materialcountries.data.Country.CountryResponse
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Path
import retrofit2.http.Query

//https://restcountries.eu/rest/
//https://restcountries.eu/rest/v2/all


interface RestCountriesService {
    @GET("all")
    fun getAllCountries(): Observable<Array<Country>>


    @GET("name/{country}")
    fun getMovieDetails(@Path("country") country: String): Observable<Array<Country>>

    @GET("regionalbloc/eu")
    fun getEUCountries(): Observable<Array<Country>>

//    @GET("")

}