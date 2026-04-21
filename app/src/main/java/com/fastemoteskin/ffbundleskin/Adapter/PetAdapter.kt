package com.fastemoteskin.ffbundleskin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Model.PetModel
import com.fastemoteskin.ffbundleskin.R

class PetAdapter(
    private val list: List<PetModel>,
    private val onItemClick: (PetModel) -> Unit   // ✅ ADD THIS
) : RecyclerView.Adapter<PetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.img)
        val name = view.findViewById<TextView>(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pet, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.img.setImageResource(item.image)
        holder.name.text = item.name

        // ✅ CLICK HANDLE
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}