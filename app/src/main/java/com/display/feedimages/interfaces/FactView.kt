package com.display.feedimages.interfaces

import com.display.feedimages.models.FactsModel

import java.util.ArrayList

/**
 * This is interface will be implemented by the View i.e. the Activity in our case
 * for receiving the callbacks from presenter class.
 */

interface FactView {

    fun showTitle(title: String)

    fun loadRecyclerView(factsModel: ArrayList<FactsModel>)

    fun changeRefreshStatus(showRefresh: Boolean)

    fun showError()
}
