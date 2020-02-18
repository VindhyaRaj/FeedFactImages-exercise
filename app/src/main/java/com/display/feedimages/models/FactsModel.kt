package com.display.feedimages.models

import android.os.Parcel
import android.os.Parcelable

import com.display.feedimages.Utils

/**
 * This is a ViewModel for the row of the recycler view of the facts feed.
 */

class FactsModel : Parcelable {

    var title: String? = null
    var description: String? = null
    var imageUrl: String? = null

    /**
     * To check whether any one of the value in the model is present.
     *
     * @return [boolean]
     */
    val isEmpty: Boolean
        get() = Utils.isEmptyOrNull(title) && Utils.isEmptyOrNull(description) && Utils.isEmptyOrNull(imageUrl)

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.title)
        dest.writeString(this.description)
        dest.writeString(this.imageUrl)
    }

    constructor() {}

    private constructor(`in`: Parcel) {
        this.title = `in`.readString()
        this.description = `in`.readString()
        this.imageUrl = `in`.readString()
    }

    /*companion object {

        val CREATOR: Parcelable.Creator<FactsModel> = object : Parcelable.Creator<FactsModel> {
            override fun createFromParcel(source: Parcel): FactsModel {
                return FactsModel(source)
            }

            override fun newArray(size: Int): Array<FactsModel?> {
                return arrayOfNulls(size)
            }
        }
    }*/

    companion object CREATOR : Parcelable.Creator<FactsModel> {
        override fun createFromParcel(parcel: Parcel): FactsModel {
            return FactsModel(parcel)
        }

        override fun newArray(size: Int): Array<FactsModel?> {
            return arrayOfNulls(size)
        }
    }
}
