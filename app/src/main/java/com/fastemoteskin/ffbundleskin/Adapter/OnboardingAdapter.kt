package com.fastemoteskin.ffbundleskin.Adapter

 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
 import android.widget.LinearLayout
 import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Model.OnboardingModel
 import com.fastemoteskin.ffbundleskin.R

class OnboardingAdapter(
    private val list: List<OnboardingModel>
) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {


    companion object {
        const val TYPE_SIMPLE = 0
        const val TYPE_VEHICLE = 1
        const val TYPE_EMOTE = 2
        const val TYPE_WHEEL = 3
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
        val desc = view.findViewById<TextView>(R.id.desc)
        val image = view.findViewById<ImageView>(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onboarding, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.title.text = item.title
        holder.desc.text = item.desc
        holder.image.setImageResource(item.image)

        // ✅ DOTS LOGIC ADD HERE
        val dotsLayout = holder.itemView.findViewById<LinearLayout>(R.id.dotsLayout)
        dotsLayout.removeAllViews()

        for (i in list.indices) {

            val dot = View(holder.itemView.context)

            val params = if (i == position) {
                // ✅ ACTIVE DOT (MOTO)
                LinearLayout.LayoutParams(70, 12)
            } else {
                // ✅ INACTIVE DOT (NANO)
                LinearLayout.LayoutParams(40, 10)
            }

            params.marginEnd = 8
            dot.layoutParams = params

            dot.setBackgroundResource(
                if (i == position) R.drawable.dot_active
                else R.drawable.dot_inactive
            )

            dotsLayout.addView(dot)
        }
    }
}