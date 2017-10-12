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
        fun publishListData(list: List<String>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        // User actions
        fun listItemClicked(sport: String?)
        // Model updates
        fun loadSportsList()
        fun checkSubscribedSportsList()
        fun onDestroy()
    }

    interface Interactor {
        fun fetchData(dataKey: String)
        fun queryUserData(dataKey: String, newData: String?)
        fun updateUserData(dataKey: String, data: List<String>)
        fun unregister()
    }

    interface InteractorOutput {
        fun onDataFetched(list: List<String>)
        fun onQueryResult(list: List<String>)
        fun onDataError()
    }
}