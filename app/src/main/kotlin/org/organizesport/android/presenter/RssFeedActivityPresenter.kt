package org.organizesport.android.presenter

import android.util.Log

import org.organizesport.android.RssFeedContract
import org.organizesport.android.entity.JokeModel
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

    override fun onViewCreated(data: Map<*, *>) {
        Log.d(TAG, "Data: $data")
        interactor?.loadRssFeed(data.keys.size.toString())
    }

    override fun onRssFeedLoaded(result: JokeModel.Result) {
        Log.d(TAG, "Joke result id = ${result.jokes.id}")
        view?.publishListData(result.jokes)
    }

    override fun onRssFeedError(error: String) {
        view?.showInfoMessage(error)
    }

    override fun noNetworkAccess() {
        view?.showInfoMessage("No network access")
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
    }
}
