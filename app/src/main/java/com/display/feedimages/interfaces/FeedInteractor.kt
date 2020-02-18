package com.display.feedimages.interfaces

import com.display.feedimages.data.FeedResponseData

/**
 * This is an interface . The class which implements this interface
 * will be doing all the network operations
 */

interface FeedInteractor {

    fun getFacts()

    fun setFactListener(factListener: FactListener)


    /**
     * This Listener will be implemented by the Presenter to receive the updates from interactor.
     */
    interface FactListener {
        fun onFactReceived(feedResponseData: FeedResponseData)

        fun errorOccurred()
    }
}
