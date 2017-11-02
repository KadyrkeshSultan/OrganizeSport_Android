package org.organizesport.android.view.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Html
import android.util.Log
import android.view.MenuItem
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_rss_feed.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

import org.jetbrains.anko.toast
import org.organizesport.android.view.BaseActivity
import org.organizesport.android.R
import org.organizesport.android.RssFeedContract
import org.organizesport.android.entity.JokeModel
import org.organizesport.android.entity.Sport
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
    private val toolbar: Toolbar? by lazy { toolbar_toolbar_view }
    private val tvId: TextView? by lazy { tv_id_activity_rss_feed }
    private val tvJoke: TextView? by lazy { tv_joke_activity_rss_feed }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rss_feed)

        presenter = RssFeedActivityPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        // add back arrow to toolbar
        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        // load invoking arguments
        val argument = intent.getSerializableExtra("data") as? Map<Sport, Boolean>
        // Passing only those entries whose 'value' is true
        argument?.let { presenter?.onViewCreated(it.filter { it.value }) }
    }

    override fun publishListData(joke: JokeModel.Joke) {
        Log.d(TAG, "publishListData: ${joke.id}, ${joke.text}")
        tvId?.text = joke.id.toString()
        tvJoke?.text = Html.fromHtml(joke.text)
    }

    override fun showInfoMessage(msg: String) {
        toast(msg)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            false
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    override fun getToolbarInstance(): Toolbar? = toolbar
}
