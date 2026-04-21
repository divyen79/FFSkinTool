package com.fastemoteskin.ffbundleskin.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fastemoteskin.ffbundleskin.R

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class PetsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pets_details)

        // ✅ SAME immersive UI
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        // ✅ SAME IDs (Character જેવી)
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val title = findViewById<TextView>(R.id.title)
        val image = findViewById<ImageView>(R.id.image)
        val desc = findViewById<TextView>(R.id.desc)

        // ✅ Back button (FIXED - double finish removed)
        btnBack.setOnClickListener {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                finish()
            }
            finish()
        }

        // ✅ Get data
        val name = intent.getStringExtra("name") ?: ""
        val img = intent.getIntExtra("image", 0)

        // ✅ Set data
        title.text = name
        image.setImageResource(img)

        // ✅ Pet description (Character જેવી function)
        desc.text = getPetDescription(name)
    }

    private fun getPetDescription(name: String): String {

        return when (name) {


            "Kactus" -> "Kactus is a survival-focused pet that continuously restores EP over time. This allows players to sustain longer fights without relying heavily on medkits, making it ideal for defensive and long-match gameplay."

            "Fang" -> "Fang increases movement speed, helping players rush enemies quickly, dodge attacks, and reposition efficiently during combat. It is perfect for aggressive gameplay styles."

            "Hoot" -> "Hoot enhances enemy detection and awareness, allowing players to track nearby opponents more effectively and avoid ambush situations."

            "Shiba" -> "Shiba marks mushrooms on the map, helping players collect EP easily. This ensures better energy management and improves overall survivability."

            "Ottero" -> "Ottero converts extra healing into EP, providing double benefits from healing items and increasing sustainability during fights."

            "Dreki" -> "Dreki reveals enemies who are using medkits nearby, giving players a tactical advantage to attack vulnerable opponents."

            "Beaston" -> "Beaston increases the throwing distance of grenades and gloo walls, allowing better tactical positioning and safer attacks."

            "Rockie" -> "Rockie reduces the cooldown of active skills, enabling players to use their abilities more frequently and gain an advantage in combat."

            "Detective Panda" -> "Detective Panda restores HP after each kill, making it ideal for aggressive players who engage in continuous fights."

            "Spirit Fox" -> "Spirit Fox reduces medkit usage time, allowing faster healing during critical combat situations."

            "Falco" -> "Falco increases skydiving speed, helping players land faster and secure loot before enemies in the early game."

            "Sensei Tig" -> "Sensei Tig improves gloo wall usage efficiency, helping players defend themselves better during intense fights."

            "Zasil" -> "Zasil provides a defensive advantage by reducing incoming damage, making players more durable in combat."

            "Night Panther" -> "Night Panther increases inventory capacity, allowing players to carry more ammo, medkits, and utility items."

            "Mr. Waggor" -> "Mr. Waggor generates gloo walls over time, ensuring players always have defensive cover available."

            "Flash" -> "Flash boosts movement speed and agility, helping players move quickly across the map and escape danger."

            "Robo" -> "Robo strengthens gloo wall durability, making them harder to break and improving defensive gameplay."

            "Moony" -> "Moony enhances skill recovery and improves the effectiveness of abilities during combat."

            "Poring" -> "Poring improves EP recovery, ensuring a steady energy supply for continuous healing support."

            "Dr. Beanie" -> "Dr. Beanie reduces damage taken from enemies, increasing survivability during intense fights."

            "Agent Hop" -> "Agent Hop restores HP after the safe zone shrinks, giving an advantage in late-game survival."

            "Pug" -> "Pug marks enemies who are using medkits, helping players locate and eliminate vulnerable opponents."

            "Finn" -> "Finn improves defensive stats, making players more resistant to damage and increasing durability."

            "Arvon" -> "Arvon boosts attack performance, increasing damage output and helping eliminate enemies faster."

            else -> "This pet has unique abilities that help players gain advantages in different combat situations."
        }
    }

    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            finish()
        }
        finish()
    }
}