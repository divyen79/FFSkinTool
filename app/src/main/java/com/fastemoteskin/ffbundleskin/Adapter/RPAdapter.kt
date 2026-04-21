package com.fastemoteskin.ffbundleskin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Model.RPModel
import com.fastemoteskin.ffbundleskin.R


class RPAdapter(
    private val list: List<RPModel>,
    private val onClick: (Int) -> Unit // ✅ CLICK LISTENER
) : RecyclerView.Adapter<RPAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rp, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title

        // ✅ CLICK HANDLE
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }
}