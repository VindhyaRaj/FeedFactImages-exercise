package com.display.feedimages

import com.display.feedimages.data.FeedResponseData
import com.display.feedimages.interfaces.FeedInteractor
import com.display.feedimages.interfaces.FactPresenter
import com.display.feedimages.interfaces.FactView
import com.display.feedimages.models.FactsModel

import java.util.ArrayList

/**
 * This class implements [FactPresenter].
 * This class does all the Business logic.
 */

class FactPresenterImpl internal constructor(private val factView: FactView, private val feedInteractor: FeedInteractor) : FactPresenter, FeedInteractor.FactListener {

    init {
        feedInteractor.setFactListener(this)
    }

    override fun getFact() {
        factView.changeRefreshStatus(true)
        feedInteractor.getFacts()
    }

    override fun onFactReceived(feedResponseData: FeedResponseData) {
        factView.changeRefreshStatus(false)
        feedResponseData.title?.let { factView.showTitle(it) }

        if (Utils.notEmptyOrNull(feedResponseData.rows)) {
            val factsModels = ArrayList<FactsModel>()
            for (row in feedResponseData.rows!!) {
                val factsModel = FactsModel()
                factsModel.title = row.title
                factsModel.description = row.description
                factsModel.imageUrl = row.imageHref
                if (!factsModel.isEmpty)
                    factsModels.add(factsModel)
            }
            factView.loadRecyclerView(factsModels)
        }
    }

    override fun errorOccurred() {
        factView.changeRefreshStatus(false)
        factView.showError()
    }
}
