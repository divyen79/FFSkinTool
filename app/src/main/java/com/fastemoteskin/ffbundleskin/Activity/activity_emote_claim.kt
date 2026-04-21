package com.fastemoteskin.ffbundleskin.Activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class activity_emote_claim : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         setContentView(R.layout.activity_emote_claim)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()


        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val img = findViewById<ImageView>(R.id.img)
        val desc = findViewById<TextView>(R.id.desc)

        btnBack.setOnClickListener {

            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            finish()
        }

        val emoteImg = intent.getIntExtra("emote_img", 0)
        val pos = intent.getIntExtra("emote_pos", 0)
        img.setImageResource(emoteImg)

        desc.text = getEmoteDescription(pos)

    }

    private fun getEmoteDescription(pos: Int): String {
        return when (pos) {

            0 -> getString(R.string.emote_1)
            1 -> getString(R.string.emote_2)
            2 -> getString(R.string.emote_3)
            3 -> getString(R.string.emote_4)
            4 -> getString(R.string.emote_5)
            5 -> getString(R.string.emote_6)
            6 -> getString(R.string.emote_7)
            7 -> getString(R.string.emote_8)
            8 -> getString(R.string.emote_9)
            9 -> getString(R.string.emote_10)
            10 -> getString(R.string.emote_11)
            11 -> getString(R.string.emote_12)

            else -> "Amazing emote with unique style and personality!"
        }
    }

    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            super.onBackPressed()
        }
        finish()
    }

}