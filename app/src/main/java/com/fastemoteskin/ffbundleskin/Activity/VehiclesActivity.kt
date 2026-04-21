package com.fastemoteskin.ffbundleskin.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Adapter.VehicleAdapter
import com.fastemoteskin.ffbundleskin.Model.VehicleModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class VehiclesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: VehicleAdapter
    val list = ArrayList<VehicleModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         setContentView(R.layout.activity_vehicles)
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
                finish()
            }
            finish()
        }

        // ✅ VEHICLES LIST (ALL FROM IMAGE)
        list.add(VehicleModel("Military Jeep", R.drawable.vehicle1))
        list.add(VehicleModel("Van", R.drawable.vehicle2))
        list.add(VehicleModel("Tuk Tuk", R.drawable.vehicle3))
        list.add(VehicleModel("Sports Car", R.drawable.vehicle4))
        list.add(VehicleModel("Monster", R.drawable.vehicle5))
        list.add(VehicleModel("Monster Truck", R.drawable.vehicle6))
        list.add(VehicleModel("Motorbike", R.drawable.vehicle7))

        // ✅ Remove duplicate
        val uniqueList = list.distinctBy { it.name }

        adapter = VehicleAdapter(uniqueList) { vehicle ->

            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                finish()
            }

                val intent = Intent(this, VehicleDetailsActivity::class.java)

                intent.putExtra("name", vehicle.name)
                intent.putExtra("image", vehicle.image)

                startActivity(intent)

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