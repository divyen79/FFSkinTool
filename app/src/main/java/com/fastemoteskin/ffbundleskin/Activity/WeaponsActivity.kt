package com.fastemoteskin.ffbundleskin.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Adapter.WeaponAdapter
import com.fastemoteskin.ffbundleskin.Model.WeaponModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class WeaponsActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: WeaponAdapter
    val list = ArrayList<WeaponModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         setContentView(R.layout.activity_weapons)
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

        // ✅ ALL 18 WEAPONS
        list.add(WeaponModel("Desert Eagle", R.drawable.w1))
        list.add(WeaponModel("M4A1", R.drawable.w2))
        list.add(WeaponModel("FGL-24", R.drawable.w3))
        list.add(WeaponModel("M500", R.drawable.w4))
        list.add(WeaponModel("Woodpecker", R.drawable.w5))
        list.add(WeaponModel("AK47", R.drawable.w6))
        list.add(WeaponModel("Grenade Launcher", R.drawable.w7))
        list.add(WeaponModel("MP40", R.drawable.w8))
        list.add(WeaponModel("Gatling", R.drawable.w9))
        list.add(WeaponModel("Gloo Melter", R.drawable.w10))

        list.add(WeaponModel("Grenade", R.drawable.w11))
        list.add(WeaponModel("M1014", R.drawable.w12))
        list.add(WeaponModel("P90", R.drawable.w13))
        list.add(WeaponModel("Pan", R.drawable.w14))
        list.add(WeaponModel("Flashbang", R.drawable.w15))
        list.add(WeaponModel("Katana", R.drawable.w16))
        list.add(WeaponModel("Healing Pistol", R.drawable.w17))
        list.add(WeaponModel("Plasma", R.drawable.w18))

        // ✅ remove duplicate
        val uniqueList = list.distinctBy { it.name }

        adapter = WeaponAdapter(uniqueList) { weapon ->

            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                finish()
            }

                val intent = Intent(this, WeaponDetailsActivity::class.java)

                intent.putExtra("name", weapon.name)
                intent.putExtra("image", weapon.image)

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