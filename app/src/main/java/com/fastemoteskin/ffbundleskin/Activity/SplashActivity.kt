package com.fastemoteskin.ffbundleskin.Activity

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fastemoteskin.ffbundleskin.MyApp
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RemoteConfigManager.init(this)
         setContentView(R.layout.activity_splash)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        if (intent.getBooleanExtra("stop_service", false) == true) {
            val notificationId = intent.getIntExtra("notification_id", -1)
            if (notificationId != -1) {
                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.cancel(notificationId)
            }
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("FCM_TOKEN", "Token: fail:${task.exception}")

                return@OnCompleteListener
            }


            val token = task.result
            Log.d("FCM_TOKEN", "Token: token:${token}")

        })


        RemoteConfigManager.fetchAndShow(this, "fbundlwebid", {
            Log.d("TAG", "fetchAndShow:splash: ${MyApp.Companion.url}")



            openActivityStart(activity_onboarding::class.java.name)

        },{
            // Next Activity (Home / Main)
            startActivity(Intent(this, activity_onboarding::class.java))
            finish()
        })
    }


}