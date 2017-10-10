package org.organizesport.android.presenter

import android.util.Log
import org.organizesport.android.BaseApplication
import org.organizesport.android.SportsListContract
import org.organizesport.android.interactor.SportsListActivityInteractor
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

    companion object {
        private val TAG: String = "SportsListPresenter"
    }

    private var interactor: SportsListContract.Interactor? = SportsListActivityInteractor(this)
    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun listItemClicked(sport: String?) {
        view?.showLoading()
        interactor?.queryUserData("sports", sport)
    }

    override fun loadSportsList() {
        interactor?.fetchData("sports")
    }

    override fun onDataFetched(list: List<String>) {
        view?.publishListData(list)
    }

    override fun onQueryResult(list: List<String>) {
        view?.hideLoading()
        interactor?.updateUserData("sports", list)
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