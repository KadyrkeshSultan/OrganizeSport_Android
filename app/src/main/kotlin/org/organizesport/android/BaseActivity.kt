package org.organizesport.android

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem

/**
 * This class extends from {@link AppCompatActivity}.
 *
 * It includes common features to be implemented by any child, such as common UI elements,
 * general settings/options, etc.
 *
 * @author pablol.
 * @since 1.0
 */
abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = "LoginActivityInteractor"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_base, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == R.id.menu_option_settings) {
            Log.d(TAG, "Settings clicked")
            true
        } else {
            false
        }
    }
}