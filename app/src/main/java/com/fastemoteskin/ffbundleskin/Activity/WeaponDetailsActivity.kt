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

class WeaponDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weapon_details)

        // ✅ Immersive UI
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

        desc.text = getWeaponDescription(name)
    }

    private fun getWeaponDescription(name: String): String {
        return when (name) {

            "Desert Eagle" -> "The Desert Eagle is a high-damage pistol known for its exceptional accuracy and stopping power. It is capable of dealing heavy damage with each shot, making it deadly in skilled hands. Although its fire rate is low, it is perfect for precise shooting and finishing enemies quickly."

            "M4A1" -> "The M4A1 is a well-balanced assault rifle that offers excellent stability, moderate damage, and good accuracy. It is suitable for both beginners and experienced players due to its low recoil and consistent performance in mid-range combat situations."

            "FGL-24" -> "The FGL-24 is a powerful grenade launcher that deals massive explosive damage over an area. It is highly effective against groups of enemies and can destroy cover easily. However, it requires careful aiming and timing due to its projectile nature."

            "M500" -> "The M500 is a unique handgun equipped with a built-in scope, allowing for long-range engagements. It combines pistol mobility with sniper-like precision, making it useful for players who prefer accuracy over rapid fire."

            "Woodpecker" -> "The Woodpecker is a high-precision rifle known for its strong armor penetration capabilities. It is ideal for taking down heavily armored enemies and performs exceptionally well in mid to long-range combat."

            "AK47" -> "The AK47 is a powerful assault rifle with high damage output, making it one of the most lethal weapons in the game. However, it comes with significant recoil, requiring good control and experience to use effectively."

            "Grenade Launcher" -> "The Grenade Launcher is an area-damage weapon designed to deal explosive damage to multiple enemies at once. It is especially useful in confined areas and for flushing out enemies from cover."

            "MP40" -> "The MP40 is a fast-firing submachine gun designed for close-range combat. It has an extremely high fire rate, allowing players to eliminate enemies quickly in short distances, though it lacks range and accuracy."

            "Gatling" -> "The Gatling gun is a heavy weapon with an incredibly high fire rate and large magazine capacity. It can suppress enemies effectively, but its movement speed is reduced while using it, making positioning important."

            "Gloo Melter" -> "The Gloo Melter is a specialized weapon designed to destroy gloo walls quickly. It is very effective in breaking enemy defenses and opening paths during intense combat situations."

            "Grenade" -> "The Grenade is a throwable explosive weapon that deals significant area damage. It is useful for attacking enemies behind cover or in buildings, and timing its throw correctly can turn the tide of battle."

            "M1014" -> "The M1014 is a powerful shotgun designed for close-range combat. It delivers high burst damage and is highly effective in tight spaces, making it a favorite choice for aggressive players."

            "P90" -> "The P90 is a submachine gun with a high magazine capacity and good fire rate. It is reliable in sustained fights and offers consistent performance in close to mid-range combat."

            "Pan" -> "The Pan is a melee weapon that also provides defensive benefits by blocking incoming damage from behind. It is useful as both an offensive and defensive tool in survival situations."

            "Flashbang" -> "The Flashbang is a tactical throwable that temporarily blinds enemies, reducing their vision and giving you a strategic advantage during fights or escapes."

            "Katana" -> "The Katana is a fast melee weapon that allows quick and silent attacks. It is effective in close combat and can be used to surprise enemies."

            "Healing Pistol" -> "The Healing Pistol is a support weapon that allows players to heal their teammates from a distance. It is extremely useful in squad gameplay and increases team survivability."

            "Plasma" -> "The Plasma gun is an advanced energy weapon that does not require reloading. Instead, it overheats with continuous use. It is effective for sustained fire but requires proper heat management."

            else -> "This weapon helps players in combat."
        }
    }

    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            finish()
        }
        finish()
    }
}