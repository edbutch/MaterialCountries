package com.edbutch.materialcountries


import android.content.Context;
import android.util.Log
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

class InfoWindowAdapter(val context: Context) : GoogleMap.InfoWindowAdapter {



    override fun getInfoWindow(marker: Marker?): View? {
        return null
    }

    override fun getInfoContents(marker: Marker?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.map_info_window, null)

        val detailsTxt = v.findViewById<TextView>(R.id.detailsText)
        val detailsBtn = v.findViewById<Button>(R.id.detailsBtn)

        // TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);

        detailsTxt.text = marker?.title
        detailsBtn.setOnClickListener {

            Log.e("KEAJAED", "ASDHUAISODJUH")
        }
        //tvLng.setText("Longitude:"+ latLng.longitude);
        return v
    }


}