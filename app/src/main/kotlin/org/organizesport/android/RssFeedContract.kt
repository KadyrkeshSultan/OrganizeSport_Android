package org.organizesport.android

/**
 * Contract interfaces for 'View' and 'Presenter' regarding {@link RssFeedActivity}
 *
 * @author pablol.
 * @since 1.0
 */
interface RssFeedContract {
    interface View {

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

    }
}