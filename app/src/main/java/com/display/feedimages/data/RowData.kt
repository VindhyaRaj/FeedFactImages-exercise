package com.display.feedimages.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * A data for each row of facts received in the [FeedResponseData]
 */
class RowData {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("imageHref")
    @Expose
    var imageHref: String? = null

}
