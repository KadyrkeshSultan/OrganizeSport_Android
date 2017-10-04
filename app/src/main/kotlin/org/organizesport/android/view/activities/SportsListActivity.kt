package org.organizesport.android.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

import kotlinx.android.synthetic.main.activity_sports_list.*

import org.jetbrains.anko.toast
import org.organizesport.android.R
import org.organizesport.android.SportsListContract
import org.organizesport.android.presenter.SportsListActivityPresenter

/**
 * This class refers to the 'SportsListActivity' View, following the MVP architectural pattern.
 *
 * The 'SportsListContract.View' interface provides associated methods for communication with the
 * Presenter only
 *
 * @author pablol.
 * @since 1.0
 */
class SportsListActivity : AppCompatActivity(), SportsListContract.View {

    companion object {
        val TAG: String = "SportsListActivity"
    }

    private var presenter: SportsListContract.Presenter? = null
    private val lvSportsList: ListView? by lazy { lv_splist_activity_sports_list }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_list)

        presenter = SportsListActivityPresenter(this)

        lvSportsList?.setOnItemClickListener { parent, view, position, id ->
            toast("item $position clicked")
        }
        lvSportsList?.adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                mutableListOf("zero", "one", "two", "three", "four",
                        "five", "six", "seven", "eight", "nine",
                        "ten", "eleven", "twelve", "thirteen", "fourteen"))

        presenter.
    }

    override fun showInfoMessage(msg: String) {
        toast(msg)
    }

    override fun publishListData(list: MutableList<String>) {
        toast("publish data")
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }
}