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
import com.fastemoteskin.ffbundleskin.Adapter.HomeAdapter
import com.fastemoteskin.ffbundleskin.Model.HomeModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HomeAdapter
    val list = ArrayList<HomeModel>()
    private var clickedPosition = -1

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: Activity started")
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        recyclerView = findViewById(R.id.recyclerView)

        // Data
        list.add(HomeModel("Diamond Guide", R.drawable.icon1))
        list.add(HomeModel("Stylish Emotes", R.drawable.icon2))
        list.add(HomeModel("Calculate Diamond", R.drawable.icon3))
        list.add(HomeModel("Get Free Diamond", R.drawable.icon4))
        list.add(HomeModel("Tips & Tricks", R.drawable.icon5))
        Log.d(TAG, "List size: ${list.size}")

        adapter = HomeAdapter(list) { position ->

            Log.d(TAG, "Item clicked position: $position")

            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                Log.d(TAG, "RemoteConfig loaded")



//            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                when (position) {

                    0 -> {
                        // Diamond Guide click


//                        startActivity(Intent(this,WebViewActivity::class.java).putExtra("ActivityName", DiamondActivity::class.java.name))
                        startActivity(Intent(this, DiamondGuideActivity::class.java))
                    }

                    1 -> {

                        startActivity(Intent(this, EmotesActivity::class.java))
                    }

                    2 -> {


                        startActivity(Intent(this, CalculateActivity::class.java))
                    }

                    3 -> {


                        startActivity(Intent(this, FreeDiamondActivity::class.java))
                    }

                    4 -> {

                        startActivity(Intent(this, TipsActivity::class.java))
                    }
                }
            }
        }


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        val btnSettings = findViewById<ImageView>(R.id.btnSettings)

        btnSettings.setOnClickListener {

            Log.d(TAG, "Settings button clicked")

            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {


//            RemoteConfigManager.fetchAndShow(this, "fbundlwebid")
                startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
    }


}