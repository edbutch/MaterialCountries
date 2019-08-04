//package com.edbutch.materialcountries
//
//import android.app.Application
//import android.content.Context
//import android.net.ConnectivityManager
//import android.net.NetworkInfo
//import android.util.Log
//
//
//class ApplicationEx : Application() {
//
//
//    companion object {
//        val instance: ApplicationEx by lazy { ApplicationEx() }
//        val hasNetwork: Boolean = instance.isNetworkConnected()
//    }
//
//
//    override fun onCreate() {
//        super.onCreate()
//
//
//    }
//
//
//    fun isNetworkConnected(): Boolean {
//        Log.e("instance", "instance")
//
//            val cm = applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            val activeNetwork = cm?.activeNetworkInfo
//            return activeNetwork != null && activeNetwork.isConnected
//
//
//    }
//
//
//}
////
////    private boolean isNetworkConnected(){
////        ConnectivityManager cm =
////        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
////
////        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
////        return activeNetwork != null &&
////                activeNetwork.isConnectedOrConnecting();
////
////
////
////
////    }