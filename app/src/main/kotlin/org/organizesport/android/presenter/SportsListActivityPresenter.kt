package org.organizesport.android.presenter

import android.os.Bundle

import org.organizesport.android.BaseApplication
import org.organizesport.android.SportsListContract
import org.organizesport.android.entity.Sport
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

    private var interactor: SportsListContract.Interactor? = SportsListActivityInteractor(this)
    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun onViewCreated() {
        view?.showLoading()
        interactor?.loadSportsList()
    }

    override fun onSaveInstanceState(outState: Bundle?, dataMap: Map<Sport, Boolean>?) {
        outState?.putSerializable("data", dataMap as? HashMap<Sport, Boolean>)
    }

    override fun onRestoreInstanceState(inState: Bundle?) {
        val data = inState?.get("data") as? Map<Sport, Boolean>
        data?.let {
            view?.publishListData(it)
        }
    }

    override fun onSportsListLoaded(map: Map<Sport, Boolean>) {
        view?.hideLoading()
        view?.publishListData(map)
    }

    override fun listItemClicked(sport: String?) {
        view?.showLoading()
        interactor?.updateUserData("sports", sport)
    }

    override fun fabClicked(dataMap: Map<Sport, Boolean>?) {
        router?.navigateTo(RssFeedActivity.TAG, dataMap)
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
