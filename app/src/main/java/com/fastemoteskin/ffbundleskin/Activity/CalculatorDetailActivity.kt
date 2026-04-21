package com.fastemoteskin.ffbundleskin.Activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class CalculatorDetailActivity : AppCompatActivity() {

    var shouldCalculate = false
    var pendingValue = ""
    lateinit var title: TextView
    lateinit var result: TextView
    lateinit var input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator_detail)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val img = findViewById<ImageView>(R.id.img)

        val btn = findViewById<Button>(R.id.btnCalculate)
        title = findViewById(R.id.title)
        result = findViewById(R.id.result)
        input = findViewById(R.id.etValue)

        btnBack.setOnClickListener {
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                super.onBackPressed()
            }
            finish()
        }

        val image = intent.getIntExtra("img", 0)
        val t = intent.getStringExtra("title")

        img.setImageResource(image)
        title.text = t

        // ✅ CALCULATE LOGIC
        btn.setOnClickListener {

            val value = input.text.toString()

            if (value.isEmpty()) {
                input.error = "Enter value"
                return@setOnClickListener
            }

            // ✅ Save value
            pendingValue = value
            shouldCalculate = true

            // ✅ First WebView open
            RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {

            }
        }

    }
    override fun onResume() {
        super.onResume()

        if (shouldCalculate) {
            shouldCalculate = false

            val diamonds = pendingValue.toInt()

            val usdPerDiamond = when (title.text.toString()) {
                "Basic" -> 0.009
                "Normal" -> 0.01
                "Advance" -> 0.012
                else -> 0.01
            }

            val usd = diamonds * usdPerDiamond
            val inr = usd * 83

            result.text = "$ %.2f (₹ %.2f)".format(usd, inr)
        }
    }
    override fun onBackPressed() {
        RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
            super.onBackPressed()
        }
        finish()
    }
}