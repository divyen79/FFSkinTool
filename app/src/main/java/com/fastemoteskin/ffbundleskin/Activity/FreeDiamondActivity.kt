package com.fastemoteskin.ffbundleskin.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Adapter.FreeDiamondAdapter
import com.fastemoteskin.ffbundleskin.Model.FreeDiamondModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.Activity.ScratchActivity
import com.fastemoteskin.ffbundleskin.Activity.SpinActivity
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class FreeDiamondActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FreeDiamondAdapter
    val list = ArrayList<FreeDiamondModel>()
    private val TAG = "FreeDiamondActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         setContentView(R.layout.activity_free_diamond)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        Log.d(TAG, "onCreate: Activity started")
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        recyclerView = findViewById(R.id.recyclerView)

        btnBack.setOnClickListener {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            finish()
        }

        // Data
        list.add(FreeDiamondModel("Spin & Win", R.drawable.f1))
        list.add(FreeDiamondModel("Scratch Card", R.drawable.f2))

        adapter = FreeDiamondAdapter(list) { item ->
            Log.d(TAG, "Item clicked: ${item.name}")

            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }

            Log.d(TAG, "RemoteConfig loaded for item click")


            if (item.name == "Spin & Win") {
                Log.d(TAG, "Opening SpinActivity")
                startActivity(Intent(this, SpinActivity::class.java))
            }
            if (item.name == "Scratch Card") {
                Log.d(TAG, "Opening ScratchActivity")
                startActivity(Intent(this, ScratchActivity::class.java)) // ✅ ADD THIS
            }

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