package com.fastemoteskin.ffbundleskin.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Adapter.RPAdapter
import com.fastemoteskin.ffbundleskin.Model.RPModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class RPLevelActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RPAdapter
    val list = ArrayList<RPModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rplevel)
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

        // ✅ DATA (same as screenshot)
        list.add(RPModel("0 - 25"))
        list.add(RPModel("26 - 40"))
        list.add(RPModel("41 - 50"))
        list.add(RPModel("51 - 60"))
        list.add(RPModel("61+"))

        val img = intent.getIntExtra("emote_img", 0)
        val pos = intent.getIntExtra("emote_pos", 0)

        adapter = RPAdapter(list) {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            val i = Intent(this, activity_emote_claim::class.java)
            i.putExtra("emote_img", img)
            i.putExtra("emote_pos", pos)

            startActivity(i)
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