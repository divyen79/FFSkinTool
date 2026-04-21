package com.fastemoteskin.ffbundleskin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Model.HomeModel
import com.fastemoteskin.ffbundleskin.R

class HomeAdapter(
    private val list: List<HomeModel>,
    private val onItemClick: (Int) -> Unit   // ✅ click listener
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon = view.findViewById<ImageView>(R.id.icon)
        val title = view.findViewById<TextView>(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.icon.setImageResource(item.icon)

        // ✅ CLICK HANDLE
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }
}