package com.display.feedimages.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * This is The response for the provided api and in GSON format
 */

class FeedResponseData {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("rows")
    @Expose
    var rows: ArrayList<RowData>? = null

}
