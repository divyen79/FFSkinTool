package com.fastemoteskin.ffbundleskin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Model.TipsModel
import com.fastemoteskin.ffbundleskin.R

class TipsAdapter(
    private val list: List<TipsModel>,
    private val onClick: (TipsModel) -> Unit
) : RecyclerView.Adapter<TipsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tips, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.title.text = list[position].title

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }
}