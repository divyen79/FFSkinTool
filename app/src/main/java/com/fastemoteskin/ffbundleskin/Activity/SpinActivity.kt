package com.fastemoteskin.ffbundleskin.Activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class SpinActivity : AppCompatActivity() {

    lateinit var wheel: ImageView
    lateinit var btnSpin: Button
    lateinit var result: TextView

    var isSpinning = false

    val items = listOf("5", "20", "25", "35", "JACKPOT", "15", "10", "30","40","Best of Luck")
    var totalDiamonds = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spin)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()



        wheel = findViewById(R.id.wheel)
        btnSpin = findViewById(R.id.btnSpin)
        result = findViewById(R.id.result)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            finish()
        }

        btnSpin.setOnClickListener {


            if (isSpinning) return@setOnClickListener
            isSpinning = true
            btnSpin.isEnabled = false

            val anglePerItem = 360f / items.size

            val targetIndex = items.indices.random()
            val targetAngle = targetIndex * anglePerItem
            val offset = anglePerItem / 2

            val currentRotation = wheel.rotation % 360
            val normalizedCurrent = (currentRotation + 360) % 360

            val finalAngle = 360 - targetAngle - offset - normalizedCurrent
            val fullSpins = (4..7).random() * 360

            val totalRotation = fullSpins + finalAngle

            val animator = ObjectAnimator.ofFloat(
                wheel,
                "rotation",
                wheel.rotation,
                wheel.rotation + totalRotation
            )

            animator.duration = 3000
            animator.interpolator = DecelerateInterpolator()

            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {

                    isSpinning = false
                    btnSpin.isEnabled = true

                    val finalRotation = wheel.rotation
                    val normalizedAngle = (finalRotation % 360 + 360) % 360

                    val index = ((360 - normalizedAngle) / anglePerItem)
                        .toInt()
                        .mod(items.size)

                    val resultValue = items[index]


                    if (resultValue == "JACKPOT") {
                        totalDiamonds += 100   // jackpot reward
                    } else if (resultValue == "Best of Luck") {
                        // no reward
                    } else {
                        val reward = resultValue.toIntOrNull() ?: 0
                        totalDiamonds += reward
                    }
                    RemoteConfigManager.fetchAndShow(this@SpinActivity, "fbundlwebid") {

                    }

                    result.text = "Total: $totalDiamonds 💎"
                }
            })

            animator.start()
        }
    }
    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            super.onBackPressed()
        }
        finish()
    }
}