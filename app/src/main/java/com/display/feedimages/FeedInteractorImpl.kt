package com.display.feedimages

import com.display.feedimages.data.FeedResponseData
import com.display.feedimages.service.ApiRequest
import com.display.feedimages.interfaces.FeedInteractor

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This class implements the [FeedInteractor] interface.
 * This class does the network call.
 */

class FeedInteractorImpl : FeedInteractor {

    private var feedListener: FeedInteractor.FactListener? = null

    override fun setFactListener(feedListener: FeedInteractor.FactListener) {
        this.feedListener = feedListener
    }

    /**
     * Makes the api call to get facts
     */
    override fun getFacts() {
        ApiRequest.getInstance().getFacts(object : Callback<FeedResponseData> {
            override fun onResponse(call: Call<FeedResponseData>, response: Response<FeedResponseData>) {
                call.request()
                feedListener!!.onFactReceived(response.body())
            }

            override fun onFailure(call: Call<FeedResponseData>, t: Throwable) {
                feedListener!!.errorOccurred()
            }
        })
    }
}
