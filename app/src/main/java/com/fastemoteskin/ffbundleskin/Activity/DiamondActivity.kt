package com.fastemoteskin.ffbundleskin.Activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge

import androidx.appcompat.app.AppCompatActivity
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class DiamondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         setContentView(R.layout.activity_diamond)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val title = findViewById<TextView>(R.id.title)
        val image = findViewById<ImageView>(R.id.image)
        val desc = findViewById<TextView>(R.id.desc)



        btnBack.setOnClickListener {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            finish()
        }

        val t = intent.getStringExtra("title") ?: "Diamond"
        val img = intent.getIntExtra("image", R.drawable.dg1)

        title.text = t
        image.setImageResource(img)

        // Same description (demo)
        desc.text = getString(R.string.diamond_desc)


    }

    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            super.onBackPressed()
        }
        finish()
    }
}