package com.example.footballleagueapplication.data.api

import android.util.Log
import com.example.footballleagueapplication.data.api.ApiService
import com.example.footballleagueapplication.configs.AppConfig
import com.example.footballleagueapplication.configs.Constants
import com.example.footballleagueapplication.configs.Constants.API_KEY
import com.example.footballleagueapplication.configs.Constants.Accept
import com.example.footballleagueapplication.configs.Constants.Content_Type
import com.example.footballleagueapplication.configs.Constants.application_json
import com.google.gson.Gson

import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"
    private const val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB
    private const val TAG = "RetrofitBuilder"

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(providerOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    private fun providerOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            cache(cache())
                // only used when network is on
                .addNetworkInterceptor(networkInterceptor())

                .addInterceptor(offlineInterceptor())
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()

    private fun offlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "offline interceptor: called.")
            var request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!AppConfig.hasNetwork()) {
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

    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "network interceptor: called.")

            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(0, TimeUnit.SECONDS)
                .build()

            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    private fun cache(): Cache {
        return Cache(
            File(AppConfig.instance?.cacheDir, "someIdentifier"),
            cacheSize
        )
    }


}