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
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        // Model updates
        fun onViewCreated(data: Map<*, *>)
        fun onDestroy()
    }

    interface Interactor {
        fun loadRssFeed(jokeId: String)
        fun unregister()
    }

    interface InteractorOutput {
        fun onRssFeedLoaded(result: JokeModel.Result)
        fun onRssFeedError(error: String)
        fun noNetworkAccess()
    }
}
