package com.edbutch.materialcountries

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.edbutch.materialcountries.data.api.Country.Country
import com.edbutch.materialcountries.data.db.AppDatabase
import com.edbutch.materialcountries.data.db.Favorite
import com.google.gson.Gson
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread


class AllCountryAdapter(val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<AllCountryAdapter.CountryViewHolder>(), Filterable {
    val TAG = "AllCountryAdapter"

    private val savingMutex = ReentrantLock()


    val instance =
        Room.databaseBuilder(layoutInflater.context.applicationContext, AppDatabase::class.java, "countriesDB").build()

    val countries: ArrayList<Country> = arrayListOf()
    var countriesSearchList: ArrayList<Country> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(layoutInflater.inflate(R.layout.allcountry_item_layout, parent, false))
    }


    override fun getItemCount(): Int {
        return countriesSearchList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {


        holder.bindModel(countriesSearchList[position])


    }

    fun setCountries(data: Array<Country>) {
        countries.addAll(data)
        countriesSearchList.addAll(data)
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        countriesSearchList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {

                    countriesSearchList = countries
                } else {
                    val filteredList = ArrayList<Country>()
                    for (row in countries) {
                        if (row.name!!.toLowerCase().contains(charString.toLowerCase())
                        ) {
                            filteredList.add(row)
                        }
                    }
                    countriesSearchList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = countriesSearchList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {

                countriesSearchList = filterResults.values as ArrayList<Country>
                notifyDataSetChanged()


            }
        }
    }


    fun favoriteAt(adapterPosition: Int) {

        notifyDataSetChanged()
        Log.e("FavoriteAt", "Adding ${countriesSearchList[adapterPosition].name} to Favorites")


        thread(start = true) {
            synchronized(savingMutex) {
                instance.favoritesDAO().insertFavorite(convertCountryToFavorite(countriesSearchList[adapterPosition]))


                val favs = instance.favoritesDAO().getFavorites()

                favs.forEach {
                    Log.e(TAG, it.name)

                }
                Log.e(TAG, "FAVORITE SIZE ${favs.size}")
            }
        }


    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.name)
        val countryRegion: TextView = itemView.findViewById(R.id.region)
        val countryPopulation: TextView = itemView.findViewById(R.id.population)
        val countryCapital: TextView = itemView.findViewById(R.id.capital)

        fun bindModel(country: Country) {

            countryName.rootView.setOnClickListener {
                val ctx = countryName.context
                ctx.startActivity(Intent(ctx, CountryActivity::class.java).putExtra("COUNTRY", country))
            }
            countryName.text = country.name
            val regionText = "Region : ${country.region}"
            countryRegion.text = regionText

            val populationText = "Population: ${country.population.toString()}"
            countryPopulation.text = populationText

            val capitalText = "Capital: ${country.capital}"
            countryCapital.text = capitalText


        }


    }

    fun convertCountryToFavorite(country: Country): Favorite {
        val languages = Gson().toJson(country.languages)
        val currencies = Gson().toJson(country.currencies)
        return Favorite(
            pKey = country.numericCode.toInt(),
            area = country.area,
            nativeName = country.nativeName,
            capital = country.capital,
            flag = country.flag,
            languages = languages,
            borders = country.borders.toString(),
            subregion = country.subregion,
            population = country.population,
            numericCode = country.numericCode,
            name = country.name,
            region = country.region,
            currencies = currencies
        )
    }


}