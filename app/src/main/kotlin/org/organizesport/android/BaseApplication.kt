package org.organizesport.android

import android.app.Application
import android.util.Log

import com.squareup.leakcanary.LeakCanary

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

import org.organizesport.android.di.DaggerModelComponent
import org.organizesport.android.di.ModelComponent
import org.organizesport.android.di.ModelModule

/**
 * This class extends from {@link Application}.
 *
 * It enables 'LeakCanary'. This library detects memory leaks for Android, automatically showing a
 * notification when a memory leak is detected in a debug build.
 *
 * The class also makes use of 'Cicerone', a library which takes control of the "Routing"
 * (Navigation) layer, in the VIPER architectural pattern.
 *
 * @author pablol.
 * @since 1.0
 */
class BaseApplication : Application() {

    companion object {
        private val TAG = "BaseApplication"
        lateinit var INSTANCE: BaseApplication
    }

    init {
        INSTANCE = this
    }

    // Routing layer (VIPER)
    lateinit var cicerone: Cicerone<Router>
    // Dagger2 component
    private val component: ModelComponent by lazy {
        DaggerModelComponent
                .builder()
                .modelModule(ModelModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        this.initCicerone()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }

        Log.d(TAG, "Installing 'LeakCanary'...")
        LeakCanary.install(this)

        // Normal app init code...
    }

    // For Dagger 2
    fun getModelComponent(): ModelComponent = this.component

    private fun BaseApplication.initCicerone(): Unit {
        this.cicerone = Cicerone.create()
    }
}
