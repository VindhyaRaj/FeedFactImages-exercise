package com.display.feedimages.service

import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * This is an Helper class which will generate a [Retrofit] singleton object
 */

internal object ApiClient {
    private val BASE_URL = "https://dl.dropboxusercontent.com/"
    private var retrofit: Retrofit? = null


    /**
     * This will generate a retrofit client and return it
     *
     * @return [Retrofit] indicates the retrofit client
     */
    val client: Retrofit?
        get() {
            if (retrofit == null) {
                val builder = OkHttpClient.Builder()
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(interceptor)

                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                        .client(builder.build())
                        .build()
            }
            return retrofit
        }
}
