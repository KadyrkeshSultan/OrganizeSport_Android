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
        fun publishListData(map: Map<String, Boolean>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        // User actions
        fun listItemClicked(sport: String?)
        fun fabClicked(dataMap: Map<String, Boolean>?)
        // Model updates
        fun onActivityCreated()
        fun onDestroy()
    }

    interface Interactor {
        fun loadSportsList()
        fun loadUserSportsList()
        fun queryUserData(dataKey: String, newData: String?)
        fun updateUserData(dataKey: String, data: List<String>)
        fun unregister()
    }

    interface InteractorOutput {
        fun onSportsListLoaded(list: List<String>)
        fun onUserSportsListLoaded(list: List<String>)
        fun onQueryResult(list: List<String>)
        fun noNetworkAccess()
        fun onDataError()
    }
}