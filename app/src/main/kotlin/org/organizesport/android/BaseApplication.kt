package org.organizesport.android

import android.app.Application
import android.util.Log
import com.squareup.leakcanary.LeakCanary

/**
 * Created by psor1i on 16-Aug-17.
 */
class BaseApplication : Application() {

    private val TAG: String? = javaClass.simpleName

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }

        Log.d(TAG, "Installing 'LeakCanary'...")
        LeakCanary.install(this)
        // Normal app init code...
    }

}
