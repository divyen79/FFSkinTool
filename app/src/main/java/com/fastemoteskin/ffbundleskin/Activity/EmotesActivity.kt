package com.fastemoteskin.ffbundleskin.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.fastemoteskin.ffbundleskin.Activity.PlayerCategoryActivity
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class EmotesActivity : AppCompatActivity() {
    lateinit var grid: GridLayout
    private val TAG = "EmotesActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_emotes)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()


        Log.d(TAG, "onCreate: Activity started")
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        grid = findViewById(R.id.grid)

        btnBack.setOnClickListener {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            finish()
        }

        // Emote images
        val emotes = listOf(

            R.drawable.e1, R.drawable.e2, R.drawable.e3,
            R.drawable.e4, R.drawable.e5, R.drawable.e6,
            R.drawable.e7, R.drawable.e8, R.drawable.e9,
            R.drawable.e10, R.drawable.e11, R.drawable.e12
        )

        val inflater = LayoutInflater.from(this)

        for ((index, img) in emotes.withIndex()) {

            val view = inflater.inflate(R.layout.item_emote, grid, false)
            val image = view.findViewById<ImageView>(R.id.img)
            image.setImageResource(img)

            view.setOnClickListener {
                Log.d(TAG, "Emote clicked index: $index")
                RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                    super.onBackPressed()
                }
                val intent = Intent(this, PlayerCategoryActivity::class.java)
                intent.putExtra("emote_img", img)
                intent.putExtra("emote_pos", index) // ✅ ADD THIS
                Log.d(TAG, "Opening PlayerCategoryActivity with index: $index")

                startActivity(intent)
            }

            grid.addView(view)
        }

    }

    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            super.onBackPressed()
        }
        finish()
    }
}