package com.edbutch.materialcountries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edbutch.materialcountries.data.db.RestCountriesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.bottomNavigationView
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class SearchActivity : AppCompatActivity() {


    val TAG = "SearchActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)



        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.eu/rest/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val apiCountries = retrofit.create(RestCountriesService::class.java)



        val countryAdapter = AllCountryAdapter(layoutInflater)
        countrySearchResult.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        countrySearchResult.adapter = countryAdapter

        countrySearchResult.layoutManager = LinearLayoutManager(this)


        val myCallback = object: ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                countryAdapter?.removeAt(viewHolder.adapterPosition)
            }
        }

        val myHelper = ItemTouchHelper(myCallback)
        myHelper.attachToRecyclerView(countrySearchResult)





        searchText.setOnClickListener { searchText.setText("") }
        searchButton.setOnClickListener {
            val text = searchText.text.toString()
            if (text == "") {

            } else {
                val search = text.replace(" ", "%20")
                searchText.setText(text)

                apiCountries.getMovieDetails(search)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        countryAdapter.setCountries(it)
                    },
                        {
                            Toast.makeText(applicationContext, it.localizedMessage, Toast.LENGTH_SHORT).show()
                        })



            }
        }




        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    // start your next activity
                    startActivity(intent)
                    true

                }

                else -> false


            }

        }
    }

    private fun onClick() {

    }


}
