package org.organizesport.android

import org.organizesport.android.entity.Sport

/**
 * Contract interfaces for 'View' and 'Presenter' regarding {@link SportsListActivity}
 *
 * @author pablol.
 * @since 1.0
 */
interface SportsListContract {
    interface View {
        fun showInfoMessage(msg: String)
        fun publishListData(map: Map<Sport, Boolean>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        // User actions
        fun listItemClicked(sport: String?)
        fun fabClicked(dataMap: Map<Sport, Boolean>?)
        // Model updates
        fun onViewCreated()
        fun onDestroy()
    }

    interface Interactor {
        fun loadSportsList()
        fun loadUserSportsList()
        fun queryUserData(dataKey: String, newData: String?)
        fun updateUserData(dataKey: String, data: List<Sport>)
        fun unregister()
    }

    interface InteractorOutput {
//        fun onSportsListLoaded(list: List<String>)
        fun onSportsListLoaded(list: List<Sport>)
        fun onUserSportsListLoaded(list: List<Sport>)
        fun onQueryResult(list: List<Sport>)
        fun noNetworkAccess()
        fun onDataError()
    }
}