package com.fastemoteskin.ffbundleskin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Model.VehicleModel
import com.fastemoteskin.ffbundleskin.R

class VehicleAdapter(
    private val list: List<VehicleModel>,
    private val onItemClick: (VehicleModel) -> Unit   // ✅ ADD
) : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.img)
        val name = view.findViewById<TextView>(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehicle, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.img.setImageResource(item.image)
        holder.name.text = item.name

        // ✅ CLICK
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}