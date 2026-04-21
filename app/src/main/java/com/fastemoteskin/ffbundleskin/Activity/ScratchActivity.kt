package com.fastemoteskin.ffbundleskin.Activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager
import com.fastemoteskin.ffbundleskin.ScratchView

class ScratchActivity : AppCompatActivity() {


    var total = 0
    var revealed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_scratch)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()



        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val result = findViewById<TextView>(R.id.result)
        val rewardText = findViewById<TextView>(R.id.tvReward)
        val lottie = findViewById<LottieAnimationView>(R.id.lottie)

        btnBack.setOnClickListener {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            finish()
        }

        // 🎯 RANDOM REWARD
        val reward = listOf(5, 10, 20, 50, 100).random()
        rewardText.text = "$reward 💎"

        // 👉 scratch complete detect (simple delay logic)
        rewardText.setOnClickListener {
            if (!revealed) {
                total += reward
                result.text = "Total: $total 💎"
                revealed = true
            }
        }


        val scratchView = findViewById<ScratchView>(R.id.scratchView)

        scratchView.setRevealListener {

            if (!revealed) {
                revealed = true

                // 🔥 SCALE ANIMATION
                rewardText.animate()
                    .scaleX(1.5f)
                    .scaleY(1.5f)
                    .setDuration(100)
                    .withEndAction {
                        rewardText.scaleX = 1f
                        rewardText.scaleY = 1f
                    }

                // ✨ FADE EFFECT
                rewardText.alpha = 0f
                rewardText.animate().alpha(1f).setDuration(500).start()

                // 🎊 CONFETTI
                lottie.visibility = View.VISIBLE
                lottie.playAnimation()
                RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                    super.onBackPressed()
                }
                // 💎 ADD TOTAL
                total += reward
                result.text = "Total: $total 💎"

                // 🎉 MESSAGE
//                Toast.makeText(
//                    this,
//                    "🎉 Congratulations! You won $reward diamonds!",
//                    Toast.LENGTH_LONG
//                ).show()

                // hide animation
                lottie.postDelayed({
                    lottie.visibility = View.GONE
                }, 3000)
            }
        }

    }

    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            super.onBackPressed()
        }
        finish()
    }


}