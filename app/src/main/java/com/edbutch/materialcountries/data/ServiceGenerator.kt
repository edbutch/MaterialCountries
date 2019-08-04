package com.edbutch.materialcountries.data

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.xml.datatype.DatatypeConstants.DAYS
import okhttp3.CacheControl
import java.util.concurrent.TimeUnit
import com.google.android.gms.common.api.Api
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.create





//TODO
//Dagger
//If not Dagger, Lifecycles could work.

class ServiceGenerator(var context : Context) {
    val TAG = "ServiceGenerator"
    val BASE_URL = "https://restcountries.eu/rest/v2/"
    val HEADER_CACHE_CONTROL = "Cache-Control"
    val HEADER_PRAGMA = "Pragma"

    val cacheSize: Long = (5 * 1024 * 1024).toLong()
    private fun cache(): Cache {
        return Cache(File(context.cacheDir, "someIdentifier"), cacheSize)
    }

//    companion object {
//        val instance: ServiceGenerator by lazy { ServiceGenerator() }
//
//    }

    fun api(): RestCountriesService {
        return retrofit.create(RestCountriesService::class.java)
    }

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOKHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    fun getOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache())
            .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
            .addNetworkInterceptor(networkInterceptor()) // only used when network is on
            .addInterceptor(offlineInterceptor())
            .build()
    }

    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }



    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "network interceptor: called.")


            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.MINUTES)
                .build()

            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d(TAG, "log: http log: $message") })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }




    private fun offlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "offline interceptor: called.")
            var request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!isNetworkConnected()) {

                Log.e(TAG, "Network Is Offline, Retrieving Cache.")
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()

                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }

            chain.proceed(request)
        }
    }


}