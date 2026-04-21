package com.fastemoteskin.ffbundleskin.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.Activity.RPLevelActivity
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class PlayerCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_player_category)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val item1 = findViewById<LinearLayout>(R.id.item1)
        val item2 = findViewById<LinearLayout>(R.id.item2)
        val item3 = findViewById<LinearLayout>(R.id.item3)

        btnBack.setOnClickListener {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            finish()
        }

        // ✅ GET DATA FROM PREVIOUS SCREEN
        val emoteImg = intent.getIntExtra("emote_img", 0)
        val pos = intent.getIntExtra("emote_pos", 0)

        // ✅ COMMON FUNCTION (clean code 🔥)
        fun openNext() {

            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            val intentNext = Intent(this, RPLevelActivity::class.java)
            intentNext.putExtra("emote_img", emoteImg)
            intentNext.putExtra("emote_pos", pos) // ✅ IMPORTANT FIX
            startActivity(intentNext)
        }

        // ✅ CLICK EVENTS

        item1.setOnClickListener {

            openNext()
        }
        item2.setOnClickListener {

            openNext()
        }
        item3.setOnClickListener {


            openNext()
        }
    }

    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            super.onBackPressed()
        }
        finish()
    }
}