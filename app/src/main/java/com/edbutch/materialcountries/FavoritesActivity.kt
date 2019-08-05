package com.edbutch.materialcountries

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.edbutch.materialcountries.data.db.AppDatabase
import com.edbutch.materialcountries.extentions.displayActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

class FavoritesActivity : AppCompatActivity() {

    lateinit var favoriteAdapter: FavoritesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        search.queryHint = "Filter your Favorites..."




        

        allCountriesView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        allCountriesView.layoutManager = LinearLayoutManager(this)

        favoriteAdapter = FavoritesAdapter(layoutInflater)
        allCountriesView.adapter = favoriteAdapter

        thread(start = true) {
            synchronized(this) {
                val favorites =
                    Room.databaseBuilder(this, AppDatabase::class.java, "countriesDB").build().favoritesDAO()
                        .getFavorites()
                favoriteAdapter.setFavorites(favorites)
            }
        }

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = allCountriesView.adapter as FavoritesAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(allCountriesView)

        bottomNavigationView.menu.get(1).setChecked(true)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
//                    val intent = Intent(this, SearchActivity::class.java)
                    // start your next activity
//                    startActivity(intent)

                    displayActivity(MainActivity::class.java)
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
                favoriteAdapter!!.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                favoriteAdapter!!.filter.filter(query)
                return false
            }
        })
    }


}

