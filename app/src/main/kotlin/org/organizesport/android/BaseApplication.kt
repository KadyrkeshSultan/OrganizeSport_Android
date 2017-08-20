package org.organizesport.android

import android.app.Application
import android.util.Log
import com.squareup.leakcanary.LeakCanary

/**
 * This class extends from {@link Application} and enables 'LeakCanary'. This library detects memory
 * leaks for Android, automatically showing a notification when an Activity memory leak is detected
 * in a debug build.
 *
 * @author psor1i
 * @since 1.0
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
