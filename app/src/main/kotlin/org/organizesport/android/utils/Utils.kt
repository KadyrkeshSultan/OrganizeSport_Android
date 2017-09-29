package org.organizesport.android.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * This class relates to utilities to be used across the whole project.
 *
 * @author pablol.
 * @since 1.0
 */
class Utils {
    companion object {
        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return cm.activeNetworkInfo != null
        }
    }
}