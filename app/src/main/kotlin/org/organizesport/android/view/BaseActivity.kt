package org.organizesport.android.view

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import org.organizesport.android.R

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
        private val TAG: String = "BaseActivity"
    }

    override fun onResume() {
        super.onResume()

        this.getToolbarInstance()?.let { this.initView(it) }
    }

    private fun initView(toolbar: Toolbar) {
        // Toolbar setup
        setSupportActionBar(toolbar)   // Replaces the 'ActionBar' with the 'Toolbar'
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

    /**
     * Gets a {@link Toolbar} instance to be used as {@link ActionBar} in the current
     * {@link Activity}. This function must be implemented by any child class.
     *
     * @return the {@link Toolbar} instance
     */
    abstract fun getToolbarInstance(): Toolbar?
}
