package com.example.iv_test_hazirah.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class ApiClient {

    companion object Factory {
        protected const val Accept = "Accept"
        protected const val ContentType = "Content-Type"
        protected const val JsonMediaType = "application/json"

        fun createApi(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://2826536e-d00d-4575-b35e-5562354bf840.mock.pstmn.io/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(getLoggingGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        private fun getClient(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.writeTimeout(30, TimeUnit.SECONDS)
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder().method(original.method(), original.body())

                requestBuilder.addHeader(Accept, JsonMediaType)
                requestBuilder.addHeader(ContentType, JsonMediaType)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            return httpClient.build()
        }

        private fun getLoggingGson(): Gson {
            return GsonBuilder().setLenient().create()
        }
    }
}