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
        fun publishListData(jokes: List<JokeModel.Jokes>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun onViewCreated(data: Map<*, *>?)
        fun onDestroy()

    }

    interface Interactor {

        fun unregister()
        fun loadRssFeed(sports: Set<Any?>?)
    }

    interface InteractorOutput {
        fun onRssFeedLoaded(result: JokeModel.Result)
    }
}