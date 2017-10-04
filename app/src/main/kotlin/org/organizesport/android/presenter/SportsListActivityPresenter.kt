package org.organizesport.android.presenter

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
        private val TAG: String = "SportsListActivityPresenter"
    }

    private var interactor: SportsListContract.Interactor? = SportsListActivityInteractor(this)
    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun listItemClicked(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadSportsList() {
        interactor?.fetchData()
    }

    override fun onDataFetched(list: MutableList<String>) {
        view?.publishListData(list)
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
    }
}