package com.edbutch.materialcountries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edbutch.materialcountries.data.Country.Country


class AllCountryAdapter(val layoutInflater: LayoutInflater, val context: Context) :
    RecyclerView.Adapter<AllCountryAdapter.CountryViewHolder>(), Filterable {

    val countries: ArrayList<Country> = arrayListOf()
    var countriesSearchList: ArrayList<Country> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(layoutInflater.inflate(R.layout.allcountry_item_layout, parent, false))
    }


    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {


            holder.bindModel(countriesSearchList[position])

    }

    fun setCountries(data: Array<Country>) {
        countries.addAll(data)
        countriesSearchList = countries
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
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
                val filterResults = Filter.FilterResults()
                filterResults.values = countriesSearchList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                countriesSearchList = filterResults.values as ArrayList<Country>
                notifyDataSetChanged()
            }
        }
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.name)
        val countryRegion: TextView = itemView.findViewById(R.id.region)
        val countryPopulation: TextView = itemView.findViewById(R.id.population)
        val countryCapital: TextView = itemView.findViewById(R.id.capital)
        val countryFlag: WebView = itemView.findViewById(R.id.flag)

        fun bindModel(country: Country) {
            countryName.text = country.name
            countryRegion.text = country.region
            countryPopulation.text = "Population: ${country.population.toString()}"
            countryCapital.text = "Capital: ${country.capital}"
            countryFlag.loadUrl(country.flag)
            countryFlag.zoomBy(20f)
            countryFlag.setBackgroundColor(0x00000000)
        }


    }


}