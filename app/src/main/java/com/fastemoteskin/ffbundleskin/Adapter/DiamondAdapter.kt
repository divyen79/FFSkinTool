package com.fastemoteskin.ffbundleskin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Model.DiamondModel
import com.fastemoteskin.ffbundleskin.R

class DiamondAdapter(
    private val list: List<DiamondModel>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<DiamondAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image)
        val btn = view.findViewById<Button>(R.id.btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_diamond, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.image.setImageResource(item.image)
        holder.btn.text = item.name


        holder.btn.setOnClickListener {
            onClick(position)
        }
    }
}