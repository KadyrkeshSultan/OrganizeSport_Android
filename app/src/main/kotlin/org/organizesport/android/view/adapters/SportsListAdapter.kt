package org.organizesport.android.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.ImageButton
import org.jetbrains.anko.find
import org.organizesport.android.R

/**
 * This class refers to the 'SportsListActivity' View, following the MVP architectural pattern.
 *
 * The 'SportsListContract.View' interface provides associated methods for communication with the
 * Presenter only
 *
 * @author pablol.
 * @since 1.0
 */
class SportsListAdapter(private val ctx: Context, resource: Int, private var dataList: ArrayList<String>) : ArrayAdapter<String>(ctx, resource, dataList) {
    // Creating a ViewHolder to speed up the performance
    private inner class ViewHolder {
        var tvSport: TextView? = null
        var imgBtnSelectedSport: ImageButton? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView

        val viewHolder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.list_view_custom_layout, null)

            // Configure a 'ViewHolder'
            viewHolder = ViewHolder()
            viewHolder.tvSport = convertView.find(R.id.tv_sport_list_view_custom_layout)
            viewHolder.imgBtnSelectedSport = convertView.find(R.id.img_btn_selected_sport_list_view_custom_layout)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        // 'viewHolder' can be now populated
        viewHolder.tvSport?.text = dataList[position]


        return convertView
    }

    override fun getCount(): Int = dataList.size
}
