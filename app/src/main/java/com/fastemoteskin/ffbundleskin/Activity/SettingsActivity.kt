package com.fastemoteskin.ffbundleskin.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnShare = findViewById<LinearLayout>(R.id.btnShare)
        val btnRate = findViewById<LinearLayout>(R.id.btnRate)
        val btnPrivacy = findViewById<LinearLayout>(R.id.btnPrivacy)


        // 🔙 Back
        btnBack.setOnClickListener {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                finish()
            }
            finish()
        }

        // 📤 Share App
        btnShare.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Download this app: https://play.google.com/store/apps/details?id=$packageName")
            startActivity(Intent.createChooser(intent, "Share App"))
        }

        // ⭐ Rate Us
        btnRate.setOnClickListener {


            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$packageName")
                    )
                )
            } catch (e: Exception) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }

        }

        // 🔐 Privacy Policy
        btnPrivacy.setOnClickListener {

            val url = "https://your-privacy-policy-link.com"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

    }
    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            super.onBackPressed()
        }
        finish()
    }

}