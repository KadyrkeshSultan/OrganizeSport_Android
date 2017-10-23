package org.organizesport.android.presenter

import org.organizesport.android.BaseApplication
import org.organizesport.android.SportsListContract
import org.organizesport.android.interactor.SportsListActivityInteractor
import org.organizesport.android.view.activities.RssFeedActivity
import ru.terrakok.cicerone.Router

/**
 * This class refers to the presenter attached to the {@link SportsListActivity} view and related
 * entities.
 *
 * @author pablol.
 * @since 1.0
 */
class SportsListActivityPresenter(private var view: SportsListContract.View?) : SportsListContract.Presenter,
        SportsListContract.InteractorOutput {

    private companion object {
        private val TAG: String = "SportsListPresenter"
    }

    private lateinit var availableSportsList: List<String>
    private var interactor: SportsListContract.Interactor? = SportsListActivityInteractor(this)
    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun onActivityCreated() {
        view?.showLoading()
        interactor?.loadSportsList()
    }

    override fun onSportsListLoaded(list: List<String>) {
        availableSportsList = list
        interactor?.loadUserSportsList()
    }

    override fun onUserSportsListLoaded(list: List<String>) {
        val map = availableSportsList.associateBy({it}, {list.contains(it)})
        interactor?.updateUserData("sports", list)
        view?.hideLoading()
        view?.publishListData(map)
    }

    override fun listItemClicked(sport: String?) {
        view?.showLoading()
        interactor?.queryUserData("sports", sport)
    }

    override fun fabClicked(dataMap: Map<String, Boolean>?) {
        router?.navigateTo(RssFeedActivity.TAG, dataMap)
    }

    override fun onQueryResult(list: List<String>) {
        view?.hideLoading()
        interactor?.updateUserData("sports", list)
    }

    override fun noNetworkAccess() {
        view?.hideLoading()
        view?.showInfoMessage("No network access")
    }

    override fun onDataError() {
        view?.showInfoMessage("Data error")
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
    }
}
