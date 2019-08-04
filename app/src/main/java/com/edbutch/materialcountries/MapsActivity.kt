package com.edbutch.materialcountries

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edbutch.materialcountries.data.Country.Country
import com.edbutch.materialcountries.data.ServiceGenerator
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    private lateinit var serviceGenerator: ServiceGenerator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        serviceGenerator = ServiceGenerator(this)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val result = serviceGenerator.api().getEUCountries()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                googleMap.setCountries(it!!)

            },
                {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                })


    }


    fun displayCountryActivity(title: String) {
        startActivity(Intent(this, CountryActivity::class.java).putExtra("COUNTRY", title))

    }

    private fun GoogleMap.setCountries(countries: Array<Country>) {

        countries.forEachIndexed { index, country ->
            val title = country.name
            val latLng = LatLng(country.latlng!![0], country.latlng[1])
            addMarker(MarkerOptions().position(latLng).title(title))


            //InfoWndowAdapter markerInfoWindowAdapter = new InfoWndowAdapter(getApplicationContext());
            //        googleMap.setInfoWindowAdapter(markerInfoWindowAdapter);

            setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener {

                it.isDraggable = false
                it.isFlat = false
            })
            val infoWindowAdapter = InfoWindowAdapter(this@MapsActivity)
            setInfoWindowAdapter(infoWindowAdapter)


        }

    }


}




