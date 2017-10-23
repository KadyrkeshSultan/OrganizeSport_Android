package org.organizesport.android.interactor

import android.content.Context
import org.organizesport.android.BaseApplication
import org.organizesport.android.RssFeedContract
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

    // Inject Dagger 2 dependencies
    init {
        BaseApplication.INSTANCE.getModelComponent().injectDependency(this)
    }

    override fun loadRssFeed(sports: Set<Any?>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unregister() {
        output = null
    }
}
