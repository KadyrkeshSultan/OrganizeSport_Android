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
        fun updateUserData(dataKey: String, sport: String?)
        fun unregister()
    }

    interface InteractorOutput {
        fun onSportsListLoaded(map: Map<Sport, Boolean>)
        fun noNetworkAccess()
        fun onDataError()
    }
}
