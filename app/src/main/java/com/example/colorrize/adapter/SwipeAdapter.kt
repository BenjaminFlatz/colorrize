package com.example.colorrize.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.colorrize.R
import com.example.colorrize.models.SwipeModel
import java.util.ArrayList


class SwipeAdapter(private val context: Context, private val ModelArrayList: ArrayList<SwipeModel>) : BaseAdapter() {

    fun remove(position: Int) {
        ModelArrayList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return ModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return ModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.remove_list_item, null, true)

            holder.tvname = convertView!!.findViewById(R.id.tv) as TextView
            holder.iv = convertView.findViewById(R.id.image) as ImageView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.tvname!!.text = ModelArrayList[position].getNames()
        holder.iv!!.setImageResource(ModelArrayList[position].getImage_drawables())

        return convertView
    }

    private inner class ViewHolder {

        var tvname: TextView? = null
        var iv: ImageView? = null

    }

}