package com.display.feedimages.service

import com.display.feedimages.data.FeedResponseData

import retrofit2.Callback

/**
 * This is an Helper class used to make an API call.
 */

class ApiRequest
/**
 * Initialize the api request and api client.
 */
private constructor() {
    private val apiInterface: ApiInterface

    init {
        apiInterface = ApiClient.client!!.create(ApiInterface::class.java!!)
    }

    /**
     * Api request to get facts
     * @param callback callback for the response of the api
     */
    fun getFacts(callback: Callback<FeedResponseData>) {
        val call = apiInterface.fact
        call.enqueue(callback)
    }

    companion object {
        private var instance: ApiRequest? = null

        /**
         * generate singleton instance of ApiRequest
         * @return [ApiRequest] indicates teh request object
         */
        fun getInstance(): ApiRequest {
            if (instance == null)
                instance = ApiRequest()
            return instance as ApiRequest
        }
    }
}
