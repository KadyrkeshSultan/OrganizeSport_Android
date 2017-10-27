package org.organizesport.android.view.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_sports_list.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

import org.jetbrains.anko.toast
import org.organizesport.android.view.BaseActivity
import org.organizesport.android.BaseApplication
import org.organizesport.android.R
import org.organizesport.android.SportsListContract
import org.organizesport.android.presenter.SportsListActivityPresenter
import org.organizesport.android.utils.addClickAction
import org.organizesport.android.view.adapters.SportsListAdapter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

/**
 * This class refers to the 'SportsListActivity' View, following the MVP architectural pattern.
 *
 * The 'SportsListContract.View' interface provides associated methods for communication with the
 * Presenter only
 *
 * @author pablol.
 * @since 1.0
 */
class SportsListActivity : BaseActivity(), SportsListContract.View {

    companion object {
        val TAG: String = "SportsListActivity"
    }

    private val navigator: Navigator? by lazy {
        object : Navigator {

            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                val data = (command.transitionData as Map<*, *>)

                when (command.screenKey) {
                    RssFeedActivity.TAG -> startActivity(Intent(this@SportsListActivity, RssFeedActivity::class.java)
                            .putExtra("data", data as HashMap))
                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }
    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }
    private val rssFeedBtn: FloatingActionButton by lazy { fab_feeds_activity_sports_list }
    private var presenter: SportsListContract.Presenter? = null
    private val lvSportsList: ListView? by lazy { lv_splist_activity_sports_list }
    private val progressBar: ProgressBar? by lazy { pb_loading_activity_sports_list }
    private val tvNoData: TextView? by lazy { tv_no_data_activity_sports_list }
    private val adapter: SportsListAdapter? by lazy {
        SportsListAdapter(
                this,
                R.layout.list_view_custom_layout,
                HashMap()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_list)

        rssFeedBtn.addClickAction({
            presenter?.fabClicked(adapter?.getDataMap())
        })
        presenter = SportsListActivityPresenter(this)

        lvSportsList?.setOnItemClickListener { _, _, position, _ ->
            Log.d(TAG, "item clicked")
            presenter?.listItemClicked(adapter?.getItem(position)?.keys?.elementAt(0))
        }
        lvSportsList?.emptyView = tvNoData   // 'View' to be shown when no data is available
        lvSportsList?.adapter = adapter

        presenter?.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(this.navigator)
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun showInfoMessage(msg: String) {
        toast(msg)
    }

    override fun publishListData(map: Map<String, Boolean>) {
        adapter?.updateData(map)
    }

    override fun showLoading() {
        progressBar?.visibility = View.VISIBLE
        lvSportsList?.isEnabled = false
    }

    override fun hideLoading() {
        progressBar?.visibility = View.GONE
        lvSportsList?.isEnabled = true
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }
}
