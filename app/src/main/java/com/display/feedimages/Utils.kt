package com.display.feedimages

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import java.util.ArrayList

/**
 * This is an Util class.
 *
 */

object Utils {
    /**
     * Returns [boolean] indicating whether the string is neither null nor empty
     *
     * @param s the string that should be check
     * @return is empty/null
     */
    fun notEmptyOrNull(s: String?): Boolean {
        return s != null && s.trim { it <= ' ' } != ""
    }

    /**
     * Returns [boolean] indicating whether the string is null or empty
     *
     * @param s the string that should be check
     * @return is empty/null
     */
    fun isEmptyOrNull(s: String?): Boolean {
        return s == null || s.trim { it <= ' ' } == ""
    }

    /**
     * Returns [boolean] indicating whether the array is neither null nor empty
     *
     * @param arrayList the array that should be checked
     * @return is empty/null
     */
    fun notEmptyOrNull(arrayList: ArrayList<*>?): Boolean {
        return arrayList != null && !arrayList.isEmpty()
    }

    /**
     * This method check if the device is connected to internet or not
     *
     * @param context The context of the invoking class.
     * @return true if internet is connected and false otherwise.
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.activeNetworkInfo
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
