package com.edbutch.materialcountries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edbutch.materialcountries.data.Country.Country
import android.widget.LinearLayout



class AllCountryAdapter(val layoutInflater: LayoutInflater, val context: Context) :
    RecyclerView.Adapter<AllCountryAdapter.CountryViewHolder>() {

    val countries: ArrayList<Country> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(layoutInflater.inflate(R.layout.allcountry_item_layout, parent, false))
    }


    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindModel(countries[position])
    }

    fun setCountries(data: Array<Country>) {
        countries.addAll(data)
        notifyDataSetChanged()
    }
    inner class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val countryName : TextView = itemView.findViewById(R.id.name)
        val countryRegion : TextView = itemView.findViewById(R.id.region)
        val countryPopulation : TextView = itemView.findViewById(R.id.population)
        val countryCapital : TextView = itemView.findViewById(R.id.capital)
        val countryFlag : WebView = itemView.findViewById(R.id.flag)

        fun bindModel(country: Country){
            countryName.text = country.name
            countryRegion.text = country.region
            countryPopulation.text = country.population.toString()
            countryCapital.text = country.capital
            countryFlag.loadUrl(country.flag)
            countryFlag.setBackgroundColor(0x00000000)

//            val height = ((context.resources.displayMetrics.densityDpi * .3)).toInt()
//            val width =  (context.resources.displayMetrics.widthPixels * .3).toInt()

            val width = (countryFlag.width * .5).toFloat()
            val height = (countryFlag.height * .5).toFloat()
//            countryFlag.layoutParams = LinearLayout.LayoutParams(
//                width,
//                (height)
//            )
            countryFlag.scaleX = width
            countryFlag.scaleY = height

        }
    }


}