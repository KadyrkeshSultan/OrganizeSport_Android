package org.organizesport.android.view.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_sports_list.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

import org.jetbrains.anko.toast
import org.organizesport.android.BaseActivity
import org.organizesport.android.R
import org.organizesport.android.SportsListContract
import org.organizesport.android.presenter.SportsListActivityPresenter
import org.organizesport.android.view.adapters.SportsListAdapter

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

    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }
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

        this.initView()

        presenter = SportsListActivityPresenter(this)

        lvSportsList?.setOnItemClickListener { parent, view, position, id ->
            Log.d(TAG, "item clicked")
            presenter?.listItemClicked(adapter?.getItem(position)?.keys?.elementAt(0))
        }
        lvSportsList?.emptyView = tvNoData   // 'View' to be shown when no data is available
        lvSportsList?.adapter = adapter

        presenter?.onActivityCreated()
    }

    private fun initView() {
        // Toolbar setup
        setSupportActionBar(toolbar)   // Replaces the 'ActionBar' with the 'Toolbar'
    }

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
