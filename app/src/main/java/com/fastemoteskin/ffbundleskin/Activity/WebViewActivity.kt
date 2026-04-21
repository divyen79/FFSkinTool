package com.fastemoteskin.ffbundleskin.Activity

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.fastemoteskin.ffbundleskin.MyApp
import com.fastemoteskin.ffbundleskin.R

class WebViewActivity : AppCompatActivity() {

    var activityClass: Class<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webView = findViewById<WebView>(R.id.webView)
//        val btnBack = findViewById<ImageView>(R.id.btnBack)
    //    val progressBar = findViewById<ProgressBar>(R.id.progressBar)


        Log.d("CHECK", intent.extras.toString())

        //  btnBack.setOnClickListener { finish() }

        val url = intent.getStringExtra("url")

        webView.settings.javaScriptEnabled = true

        // Page load start/end
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
           //     progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
         //       progressBar.visibility = View.GONE
            }
        }

        // Progress update
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {

                if (newProgress < 100) {
               //    progressBar.visibility = View.VISIBLE
       //             progressBar.progress = newProgress
                } else {
           //         progressBar.progress = 100

                    // delay for smooth effect
           //         progressBar.postDelayed({
           //             progressBar.visibility = View.GONE
            //        }, 300)
                }
            }
        }

        if (url != null) {
            webView.loadUrl(url)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {


                var activity = intent.getStringExtra("ActivityName")
                Log.d("TAG", "fetchAndShow:splash 2: ${activity}")

                if (!activity.isNullOrEmpty()) {
                    openActivity(intent.getStringExtra("ActivityName"))
                } else {
                    finish()
                }
            }

        })

    }
}