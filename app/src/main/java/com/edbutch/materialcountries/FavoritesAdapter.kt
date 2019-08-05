package com.edbutch.materialcountries

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.edbutch.materialcountries.data.db.AppDatabase
import com.edbutch.materialcountries.data.db.Favorite
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread


class FavoritesAdapter(val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>(), Filterable {
    val TAG = "AllCountryAdapter"

    private val mutex = ReentrantLock()


    val instance =
        Room.databaseBuilder(layoutInflater.context.applicationContext, AppDatabase::class.java, "countriesDB").build()

    val favorites: ArrayList<Favorite> = arrayListOf()
    var favoriteSearchList: ArrayList<Favorite> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(layoutInflater.inflate(R.layout.allcountry_item_layout, parent, false))
    }


    override fun getItemCount(): Int {
        return favoriteSearchList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindModel(favoriteSearchList[position])
    }

    fun setFavorites(data: List<Favorite>) {

        if (favorites.size == 0 && favoriteSearchList.size == 0) {
            favorites.clear()
            favoriteSearchList.clear()
        }
        favorites.addAll(data)
        favoriteSearchList.addAll(data)
        notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {

                    favoriteSearchList = favorites
                } else {
                    val filteredList = ArrayList<Favorite>()
                    for (row in favorites) {
                        if (row.name!!.toLowerCase().contains(charString.toLowerCase())
                        ) {
                            filteredList.add(row)
                        }
                    }
                    favoriteSearchList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = favoriteSearchList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {

                favoriteSearchList = filterResults.values as ArrayList<Favorite>
                notifyDataSetChanged()


            }
        }
    }


    fun removeAt(position: Int) {
        thread(start = true) {
            synchronized(mutex) {
                instance.favoritesDAO().deleteFavorite(favoriteSearchList[position])
            }
        }
        favoriteSearchList.removeAt(position)
        favorites.removeAt(position)
        notifyItemRemoved(position)
    }


    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.name)
        val countryRegion: TextView = itemView.findViewById(R.id.region)
        val countryPopulation: TextView = itemView.findViewById(R.id.population)
        val countryCapital: TextView = itemView.findViewById(R.id.capital)

        fun bindModel(favorite: Favorite) {

            countryName.rootView.setOnClickListener {
                val ctx = countryName.context
                ctx.startActivity(Intent(ctx, CountryActivity::class.java).putExtra("Favorite", favorite))
            }
            countryName.text = favorite.name
            val regionText = "Region : ${favorite.region}"
            countryRegion.text = regionText

            val populationText = "Population: ${favorite.population.toString()}"
            countryPopulation.text = populationText

            val capitalText = "Capital: ${favorite.capital}"
            countryCapital.text = capitalText

        }
    }

}