package com.fastemoteskin.ffbundleskin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Model.CharacterModel
import com.fastemoteskin.ffbundleskin.R

class CharacterAdapter(
    private val list: List<CharacterModel>,
    private val onClick: (CharacterModel) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.img)
        val name = view.findViewById<TextView>(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.img.setImageResource(item.image)
        holder.name.text = item.name


        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }
}