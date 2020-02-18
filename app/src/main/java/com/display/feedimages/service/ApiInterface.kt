package com.display.feedimages.service

import com.display.feedimages.data.FeedResponseData

import retrofit2.Call
import retrofit2.http.GET

/**
 *
 * The Api is declared in this interfaced
 */

internal interface ApiInterface {
    @get:GET("s/2iodh4vg0eortkl/facts.json")
    val fact: Call<FeedResponseData>
}
