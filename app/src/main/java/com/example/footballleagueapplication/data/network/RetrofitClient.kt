package com.example.footballleagueapplication.data.network

import android.util.Log
import com.example.footballleagueapplication.Constants
import com.example.footballleagueapplication.Constants.HEADER_CACHE_CONTROL
import com.example.footballleagueapplication.Constants.HEADER_PRAGMA
import com.example.footballleagueapplication.Constants.TAG
import com.example.footballleagueapplication.Constants.cacheSize
import com.example.footballleagueapplication.MyApplication
import com.example.footballleagueapplication.MyApplication.Companion.hasNetwork
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import java.io.File
import java.util.concurrent.TimeUnit


class RetrofitClient {
    private val retrofit: Retrofit


    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .callFactory(okHttpClient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    }

    companion object {

        @Volatile
        private var mInstance: RetrofitClient? = null


        fun getInstance() = mInstance ?: synchronized(this) {
            mInstance ?: RetrofitClient().also { mInstance = it }

        }


        private fun okHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
                .addNetworkInterceptor(networkInterceptor()) // only used when network is on
                .addInterceptor(offlineInterceptor())
                .build()
        }


        private fun offlineInterceptor(): Interceptor {
            return Interceptor { chain ->
                Log.d(TAG, "offline interceptor: called.")
                var request = chain.request()

                // prevent caching when network is on. For that we use the "networkInterceptor"
                if (!hasNetwork()) {
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
//
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
        }

        private fun cache(): Cache {
            return Cache(File(MyApplication.instance?.cacheDir, "someIdentifier"), cacheSize)
        }


        private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.d(TAG, "log: http log: $message") }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }
    }


    val api: Api
        get() = retrofit.create(Api::class.java)

}

