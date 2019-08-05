package com.edbutch.materialcountries

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.edbutch.materialcountries.data.api.Country.Country
import com.edbutch.materialcountries.data.db.Favorite
import kotlinx.android.synthetic.main.activity_country.*



class CountryActivity : AppCompatActivity() {
    val TAG = "CountryActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        intent?.getSerializableExtra("COUNTRY")?.let {
            val country = it as Country
            setCountryView(country)

        }
        intent?.getSerializableExtra("FAVORITE")?.let {
            val favorite = it as Favorite
            setFavoriteView(favorite)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setCountryView(country: Country) {
        Log.e(TAG, country.toString())


        supportActionBar?.title = country.name
        countryNameTxt.text = country.name
        val capital = "Capital : ${country.capital}"
        countryCapitalTxt.text = capital

        val countryRegion = "Region : ${country.region}"
        countryRegionTxt.text = countryRegion
        val countrySubRegion = "Sub Region : ${country.subregion}"

        countrySubRegionTxt.text = countrySubRegion

//        val currencyTxt = "Currency : ${country.currencies.toString() }}"
        val currencyTxt = "Currency : ${country.currencies?.get(0)?.name }"
        countryCurrencyTxt.text = currencyTxt


    }

    private fun setFavoriteView(favorite: Favorite) {

        supportActionBar?.title = favorite.name
        countryNameTxt.text = favorite.name
        val capital = "Capital : ${favorite.capital}"
        countryCapitalTxt.text = capital

        val countryRegion = "Region : ${favorite.region}"
        countryRegionTxt.text = countryRegion
        val countrySubRegion = "Sub Region : ${favorite.subregion}"

        countrySubRegionTxt.text = countrySubRegion

//        val currencyTxt = "Currency : ${country.currencies.toString() }}"
        val currencyTxt = "Currency : ${favorite.currencies }"
        countryCurrencyTxt.text = currencyTxt

    }





}
