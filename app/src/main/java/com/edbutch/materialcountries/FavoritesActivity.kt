package com.edbutch.materialcountries

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edbutch.materialcountries.extentions.displayActivity
import kotlinx.android.synthetic.main.activity_main.*

class FavoritesActivity : AppCompatActivity() {

    lateinit var countryAdapter: AllCountryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)







        allCountriesView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        allCountriesView.layoutManager = LinearLayoutManager(this)

        countryAdapter = AllCountryAdapter(layoutInflater)
        allCountriesView.adapter = countryAdapter


        val swipeHandler = object : SwipeToFavoriteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = allCountriesView.adapter as AllCountryAdapter
                adapter.favoriteAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(allCountriesView)


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
//                    val intent = Intent(this, SearchActivity::class.java)
                    // start your next activity
//                    startActivity(intent)
                    true

                }

                R.id.nav_favs -> {
                    true
                }
                R.id.nav_map -> {
                    displayActivity(MapsActivity::class.java)
                    true
                }

                else -> false


            }

        }

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

