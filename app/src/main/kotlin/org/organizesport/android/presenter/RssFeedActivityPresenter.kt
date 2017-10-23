package org.organizesport.android.presenter

import android.util.Log
import org.organizesport.android.RssFeedContract
import org.organizesport.android.interactor.RssFeedActivityInteractor

/**
 * This class refers to the presenter attached to the {@link RssFeedActivity} view and related
 * entities.
 *
 * @author pablol.
 * @since 1.0
 */
class RssFeedActivityPresenter(private var view: RssFeedContract.View?) : RssFeedContract.Presenter, RssFeedContract.InteractorOutput {

    private companion object {
        val TAG: String = "RssFeedPresenter"
    }

    private var interactor: RssFeedActivityInteractor? = RssFeedActivityInteractor(this)

    override fun onViewCreated(data: Map<*, *>?) {
        Log.d(TAG, "Data: $data")
        interactor?.loadRssFeed(data?.keys)
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
    }
}