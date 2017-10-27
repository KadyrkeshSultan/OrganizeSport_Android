package org.organizesport.android

import org.organizesport.android.entity.JokeModel

/**
 * Contract interfaces for 'View' and 'Presenter' regarding {@link RssFeedActivity}
 *
 * @author pablol.
 * @since 1.0
 */
interface RssFeedContract {
    interface View {
        fun publishListData(joke: JokeModel.Joke)
        fun showLoading()
        fun hideLoading()
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        fun onViewCreated(data: Map<*, *>)
        fun onDestroy()
    }

    interface Interactor {
        fun loadRssFeed(jokeId: String)
        fun unregister()
    }

    interface InteractorOutput {
        fun onRssFeedLoaded(result: JokeModel.Result)
        fun noNetworkAccess()
    }
}