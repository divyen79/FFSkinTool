package com.fastemoteskin.ffbundleskin.Activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         setContentView(R.layout.activity_character_detail)
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
                finish()
            }
            finish()
        }

        val name = intent.getStringExtra("name") ?: ""
        val img = intent.getIntExtra("image", 0)

        title.text = name
        image.setImageResource(img)

        //   Different description per character
        desc.text = getCharacterDescription(name)

    }

    private fun getCharacterDescription(name: String): String {

        return when (name) {

            "Luna" -> getString(R.string.desc_luna)
            "Jai" -> getString(R.string.desc_jai)
            "Moco" -> getString(R.string.desc_moco)
            "Chrono" -> getString(R.string.desc_chrono)
            "K" -> getString(R.string.desc_k)
            "Skyler" -> getString(R.string.desc_skyler)
            "Iris" -> getString(R.string.desc_iris)
            "D-Bee" -> getString(R.string.desc_dbee)
            "Xayne" -> getString(R.string.desc_xayne)
            "Dasha" -> getString(R.string.desc_dasha)
            "Antonio" -> getString(R.string.desc_antonio)
            "Jota" -> getString(R.string.desc_jota)
            "Kelly" -> getString(R.string.desc_kelly)
            "Wolfrahh" -> getString(R.string.desc_wolfrahh)
            "Hayato" -> getString(R.string.desc_hayato)
            "Maro" -> getString(R.string.desc_maro)
            "Steffie" -> getString(R.string.desc_steffie)
            "Moco (Awakened)" -> getString(R.string.desc_moco_awakened)

            else -> getString(R.string.desc_default)
        }
    }
    override fun onBackPressed() {

        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            finish()
        }
        finish()
    }
}