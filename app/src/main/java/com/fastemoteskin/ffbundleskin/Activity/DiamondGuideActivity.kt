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
import com.fastemoteskin.ffbundleskin.Adapter.DiamondAdapter
import com.fastemoteskin.ffbundleskin.Model.DiamondModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class DiamondGuideActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: DiamondAdapter
    val list = ArrayList<DiamondModel>()

    private val TAG = "DiamondGuideActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_diamond_guide)
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
        list.add(DiamondModel("Diamonds", R.drawable.dg1))
        list.add(DiamondModel("Characters", R.drawable.dg2))
        list.add(DiamondModel("Pets", R.drawable.dg3))
        list.add(DiamondModel("Vehicles", R.drawable.dg4))
        list.add(DiamondModel("Weapons", R.drawable.dg5))
        Log.d(TAG, "List size: ${list.size}")

        adapter = DiamondAdapter(list) { position ->
            Log.d(TAG, "Item clicked position: $position")

            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }

                when (position) {

                    0 -> startActivity(Intent(this, DiamondActivity::class.java))

                    1 -> startActivity(Intent(this, CharacterActivity::class.java))

                    2 -> startActivity(Intent(this, PetsActivity::class.java))

                    3 -> startActivity(Intent(this, VehiclesActivity::class.java))

                    4 -> startActivity(Intent(this, WeaponsActivity::class.java))
                }


        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter



    }
    override fun onBackPressed() {

        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
           finish()
        }
        finish()
    }

}