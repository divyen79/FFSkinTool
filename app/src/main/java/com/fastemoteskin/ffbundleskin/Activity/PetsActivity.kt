package com.fastemoteskin.ffbundleskin.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastemoteskin.ffbundleskin.Adapter.PetAdapter
import com.fastemoteskin.ffbundleskin.Model.PetModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class PetsActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PetAdapter
    val list = ArrayList<PetModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         setContentView(R.layout.activity_pets)

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

        list.add(PetModel("Kactus", R.drawable.pet1))
        list.add(PetModel("Fang", R.drawable.pet2))
        list.add(PetModel("Hoot", R.drawable.pet3))
        list.add(PetModel("Shiba", R.drawable.pet4))
        list.add(PetModel("Ottero", R.drawable.pet5))
        list.add(PetModel("Dreki", R.drawable.pet6))
        list.add(PetModel("Beaston", R.drawable.pet7))
        list.add(PetModel("Rockie", R.drawable.pet8))
        list.add(PetModel("Detective Panda", R.drawable.pet9))
        list.add(PetModel("Spirit Fox", R.drawable.pet10))

        list.add(PetModel("Falco", R.drawable.pet11))
        list.add(PetModel("Sensei Tig", R.drawable.pet12))
        list.add(PetModel("Zasil", R.drawable.pet13))
        list.add(PetModel("Night Panther", R.drawable.pet14))
        list.add(PetModel("Mr. Waggor", R.drawable.pet15))
        list.add(PetModel("Flash", R.drawable.pet16))
        list.add(PetModel("Robo", R.drawable.pet17))
        list.add(PetModel("Moony", R.drawable.pet18))
        list.add(PetModel("Poring", R.drawable.pet19))
        list.add(PetModel("Dr. Beanie", R.drawable.pet20))

        list.add(PetModel("Agent Hop", R.drawable.pet21))
        list.add(PetModel("Pug", R.drawable.pet22))
        list.add(PetModel("Finn", R.drawable.pet23))
        list.add(PetModel("Arvon", R.drawable.pet24))

        // ✅ REMOVE DUPLICATE
        val uniqueList = list.distinctBy { it.name }

        adapter = PetAdapter(uniqueList) { pet ->


            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                finish()
            }
                val intent = Intent(this, PetsDetailsActivity::class.java)


                intent.putExtra("name", pet.name)
                intent.putExtra("image", pet.image)

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