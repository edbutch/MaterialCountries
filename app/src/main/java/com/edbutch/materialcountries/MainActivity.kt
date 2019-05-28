package com.edbutch.materialcountries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edbutch.materialcountries.data.RestCountriesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    lateinit var countryAdapter: AllCountryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        countryAdapter = AllCountryAdapter(layoutInflater, this)
        allCountriesView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        allCountriesView.adapter = countryAdapter

        allCountriesView.layoutManager = LinearLayoutManager(this)





        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.eu/rest/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val apiCountries = retrofit.create(RestCountriesService::class.java)

        apiCountries.getAllCountries()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({



                countryAdapter.setCountries(it)
            },
                {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                })

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                countryAdapter!!.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                countryAdapter!!.filter.filter(query)
                return false
            }
        })
    }



    }

