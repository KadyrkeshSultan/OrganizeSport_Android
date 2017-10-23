package org.organizesport.android.view.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*
import org.organizesport.android.view.BaseActivity
import org.organizesport.android.R
import org.organizesport.android.RssFeedContract
import org.organizesport.android.presenter.RssFeedActivityPresenter

/**
 * This class refers to the 'RssFeedActivity' View, following the MVP (VIPER) architectural pattern.
 *
 * The 'RssFeedContract.View' interface provides associated methods for communication with the
 * Presenter only
 *
 * @author pablol.
 * @since 1.0
 */
class RssFeedActivity : BaseActivity(), RssFeedContract.View {

    companion object {
        val TAG: String = "RssFeedActivity"
    }

    private var presenter: RssFeedContract.Presenter? = null
    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rss_feed)

        presenter = RssFeedActivityPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter?.onViewCreated(intent.getSerializableExtra("data")?.let { it as HashMap<*, *> })
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun getToolbarInstance(): Toolbar? = toolbar
}