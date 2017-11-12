package org.organizesport.android.interactor

import android.content.Context
import android.util.Log

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import org.organizesport.android.BaseApplication
import org.organizesport.android.RssFeedContract
import org.organizesport.android.utils.isNetworkConnected
import org.organizesport.android.utils.retrofit2.JokesAPIService
import org.organizesport.android.utils.retrofit2.Retrofit2Client

import javax.inject.Inject

/**
 * This class refers to the interactor attached to the {@link RssFeedActivity} view and related
 * entities.
 *
 * @author pablol.
 * @since 1.0
 */
class RssFeedActivityInteractor(private var output: RssFeedContract.InteractorOutput?) : RssFeedContract.Interactor {

    companion object {
        private val TAG: String = "RssFeedInteractor"
    }

    @Inject
    lateinit var context: Context
    private val jokesAPIService: JokesAPIService by lazy { Retrofit2Client.create("https://api.icndb.com/") }
    private var disposable: Disposable? = null

    // Inject Dagger 2 dependencies
    init {
        BaseApplication.INSTANCE.getModelComponent().injectDependency(this)
    }

    override fun loadRssFeed(jokeId: String) {
        if (isNetworkConnected(context)) {
            disposable = jokesAPIService.loadSports(jokeId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> output?.onRssFeedLoaded(result) },
                            { error ->
                                Log.w(TAG, "Error: ${error.message}")
                                output?.onRssFeedError("Entry not found!")
                            }
                    )
        } else {
            output?.noNetworkAccess()
        }
    }

    override fun unregister() {
        output = null
    }
}
