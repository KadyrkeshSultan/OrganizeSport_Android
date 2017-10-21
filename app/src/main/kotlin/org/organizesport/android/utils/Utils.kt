package org.organizesport.android.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * This file relates to utility functions to be used across the whole project.
 *
 * @author pablol.
 * @since 1.0
 */
fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return cm.activeNetworkInfo != null
}

/**
 * This extension function allows to assign a lambda expression to a 'click' event on any 'View'.
 *
 * @author pablol.
 * @since 1.0
 */
fun View.addClickAction(f: () -> Unit): Unit {
    this.clicks()
            .observeOn(AndroidSchedulers.mainThread())   // subscribers post on the UI thread
            .subscribe { f() }
}
