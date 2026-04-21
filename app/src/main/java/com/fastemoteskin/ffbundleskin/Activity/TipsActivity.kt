package com.fastemoteskin.ffbundleskin.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Adapter.TipsAdapter
import com.fastemoteskin.ffbundleskin.Model.TipsModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class TipsActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TipsAdapter
    val list = ArrayList<TipsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         setContentView(R.layout.activity_tips)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        recyclerView = findViewById(R.id.recyclerView)

        btnBack.setOnClickListener {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            finish()
        }

        // Data
        list.add(TipsModel(
            "Choose the correct landing point",
            "Landing is one of the most important parts of the game. Always choose a location that has enough loot but less enemies. Avoid hot drops if you are a beginner. Try landing near buildings to quickly collect weapons and gear. A good landing decision increases your survival chances significantly."
        ))

        list.add(TipsModel(
            "Get Device",
            "Having a good device improves your gameplay experience. A smooth device helps in better aiming, faster reaction, and stable FPS. Always optimize your device settings, close background apps, and use game mode if available."
        ))

        list.add(TipsModel(
            "Explore military sites",
            "Military areas usually contain high-level loot such as powerful weapons, armor, and attachments. However, these places are dangerous because many players land there. Go prepared and always stay alert while looting."
        ))

        list.add(TipsModel(
            "Pay attention in safe zone",
            "Always keep an eye on the safe zone. Staying outside the zone will reduce your health quickly. Plan your movement early and avoid running at the last moment. Use vehicles if the zone is far."
        ))

        list.add(TipsModel(
            "Use the vehicle carefully",
            "Vehicles help in fast travel but also make noise which can reveal your location. Use them wisely and avoid driving directly into enemies. Always park in a safe place before engaging in fights."
        ))

        adapter = TipsAdapter(list) { item ->
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            val intent = Intent(this, TipsDetailActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("desc", item.desc)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


    }

    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            super.onBackPressed()
        }
        finish()
    }


}