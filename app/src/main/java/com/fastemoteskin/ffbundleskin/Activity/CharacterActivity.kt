package com.fastemoteskin.ffbundleskin.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Adapter.CharacterAdapter
import com.fastemoteskin.ffbundleskin.Activity.CharacterDetailActivity
import com.fastemoteskin.ffbundleskin.Model.CharacterModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class CharacterActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CharacterAdapter
    val list = ArrayList<CharacterModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         setContentView(R.layout.activity_character)
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

        list.add(CharacterModel("Jai", R.drawable.character1))
        list.add(CharacterModel("Moco", R.drawable.character2))
        list.add(CharacterModel("Steffie", R.drawable.character3))
        list.add(CharacterModel("Chrono", R.drawable.character4))
        list.add(CharacterModel("K", R.drawable.character5))
        list.add(CharacterModel("Luna", R.drawable.character6))
        list.add(CharacterModel("Skyler", R.drawable.character7))
        list.add(CharacterModel("Iris", R.drawable.character8))
        list.add(CharacterModel("D-Bee", R.drawable.character9))
        list.add(CharacterModel("Xayne", R.drawable.character10))

        list.add(CharacterModel("Dasha", R.drawable.character11))
        list.add(CharacterModel("Antonio", R.drawable.character12))
        list.add(CharacterModel("Jota", R.drawable.character13))
        list.add(CharacterModel("Kelly", R.drawable.character14))
        list.add(CharacterModel("Moco (Awakened)", R.drawable.character15))
        list.add(CharacterModel("Wolfrahh", R.drawable.character16))
        list.add(CharacterModel("Hayato", R.drawable.character17))
        list.add(CharacterModel("Maro", R.drawable.character18))

        // 👉 Duplicate remove (safety)
        val uniqueList = list.distinctBy { it.name }

        adapter = CharacterAdapter(uniqueList) { item ->
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                finish()
            }
            val intent = Intent(this, CharacterDetailActivity::class.java)
            intent.putExtra("name", item.name)
            intent.putExtra("image", item.image)

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