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
import com.fastemoteskin.ffbundleskin.Adapter.CalculateAdapter
import com.fastemoteskin.ffbundleskin.Activity.CalculatorDetailActivity
import com.fastemoteskin.ffbundleskin.Model.CalculateModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class CalculateActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CalculateAdapter
    val list = ArrayList<CalculateModel>()

    private val TAG = "CalculateActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculate)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        recyclerView = findViewById(R.id.recyclerView)
        Log.d(TAG, "onCreate: Activity started")
        btnBack.setOnClickListener {

            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }

            finish()
        }

        // Data
        list.add(CalculateModel("Basic", R.drawable.c1))
        list.add(CalculateModel("Normal", R.drawable.c2))
        list.add(CalculateModel("Advance", R.drawable.c3))

        adapter = CalculateAdapter(list) { item ->
            Log.d(TAG, "Item clicked: ${item.name}")
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            Log.d(TAG, "RemoteConfig loaded for item click")

            val intent = Intent(this, CalculatorDetailActivity::class.java)

            intent.putExtra("img", item.image)
            intent.putExtra("title", item.name)
            Log.d(TAG, "Opening CalculatorDetailActivity with ${item.name}")

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