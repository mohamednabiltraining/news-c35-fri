package com.route.newappc35fri.api

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class ApiManager {
    companion object {
        private val BASE_URL = "https://newsapi.org/";
        private var retrofit: Retrofit? = null;
        private fun getInstance(): Retrofit {
            if (retrofit == null) {
                // createRetrofit

                val logging = HttpLoggingInterceptor(
                    object : HttpLoggingInterceptor.Logger {
                        override fun log(message: String) {
                            Log.e("api", message)
                        }
                    }
                )
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            }
            return retrofit!!;
        }

        fun getApis(): Services {
            return getInstance().create(Services::class.java);
        }
    }
}