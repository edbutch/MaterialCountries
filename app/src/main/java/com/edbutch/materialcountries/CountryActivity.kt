package com.edbutch.materialcountries

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.edbutch.materialcountries.data.db.Country.Country
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




}
