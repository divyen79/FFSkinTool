package com.fastemoteskin.ffbundleskin.Activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class VehicleDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vehicle_details)

        // ✅ SAME immersive UI
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

        desc.text = getVehicleDescription(name)
    }

    private fun getVehicleDescription(name: String): String {
        return when (name) {

            "Military Jeep" -> "The Military Jeep is a well-balanced vehicle offering a great combination of speed, durability, and control. It is ideal for squad gameplay as it can carry multiple players comfortably. The jeep performs well on both roads and rough terrains like hills and grasslands, making it reliable for rotations. It provides moderate protection against enemy fire, making it a safe choice for both beginners and experienced players."

            "Van" -> "The Van is known for its high durability and is one of the best vehicles for transporting a full squad safely. It can absorb a large amount of damage, making it useful as moving cover during fights. However, its slow speed makes it less suitable for quick rotations. It is best used for defensive gameplay and safe zone movement with teammates."

            "Tuk Tuk" -> "The Tuk Tuk is a small and compact vehicle that offers decent speed and excellent maneuverability in tight spaces. It is especially useful in crowded areas and narrow paths where larger vehicles cannot easily move. While it is good for quick escapes, it has very low durability and can be destroyed quickly by enemy fire."

            "Sports Car" -> "The Sports Car is the fastest vehicle in the game, designed for rapid movement and quick rotations across the map. It has excellent acceleration and top speed, allowing players to reach safe zones quickly. However, its low durability makes it vulnerable to enemy attacks, and its high speed can make it difficult to control for inexperienced players."

            "Monster" -> "The Monster vehicle is built for aggressive gameplay and is capable of crushing enemies due to its strong build. It has high durability and can withstand significant damage in combat situations. Although its speed is moderate, it is highly effective in open areas where players can use it to dominate enemies and create pressure during fights."

            "Monster Truck" -> "The Monster Truck is a heavy-duty vehicle with extremely high durability and excellent off-road performance. It can easily travel over rough terrain, obstacles, and hills without losing control. This vehicle is perfect for long fights and squad pushes, as it can absorb a lot of damage while keeping players safe inside."

            "Motorbike" -> "The Motorbike is a fast and agile vehicle, best suited for solo players who need quick movement and flexibility. It can easily navigate narrow paths and uneven terrain. While it offers high speed and mobility, it has very low durability, making it risky in combat situations. It is ideal for scouting, quick rotations, and escaping dangerous zones."

            else -> "This vehicle helps players travel faster across the map."
        }
    }

    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            finish()
        }
        finish()
    }
}