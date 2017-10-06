package org.organizesport.android

/**
 * Contract interfaces for 'View' and 'Presenter' regarding {@link SportsListActivity}
 *
 * @author pablol.
 * @since 1.0
 */
interface SportsListContract {
    interface View {
        fun showInfoMessage(msg: String)
        fun publishListData(list: MutableList<String>)
    }

    interface Presenter {
        // User actions
        fun listItemClicked(position: Int)
        // Model updates
        fun loadSportsList()
        fun onDestroy()
    }

    interface Interactor {
        fun fetchData()
        fun unregister()
    }

    interface InteractorOutput {
        fun onDataFetched(list: MutableList<String>)
        fun onDataError()
    }
}