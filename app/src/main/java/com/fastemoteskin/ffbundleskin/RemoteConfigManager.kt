package com.fastemoteskin.ffbundleskin


import android.content.Context
import android.content.Intent
import android.util.Log
import com.fastemoteskin.ffbundleskin.Activity.WebViewActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

object RemoteConfigManager {

     lateinit var remoteConfig: FirebaseRemoteConfig


    fun init(context: Context) {

        if (FirebaseApp.getApps(context).isEmpty()) {
            FirebaseApp.initializeApp(context)
        }

        remoteConfig = FirebaseRemoteConfig.getInstance()
    }

    fun fetchAndShow(context: Context, key: String, function: () -> Unit) {
        remoteConfig.fetchAndActivate().addOnCompleteListener {

            if (it.isSuccessful) {

                MyApp.Companion.url = remoteConfig.getString(key)
                Log.d("TAG", "fetchAndShow: ${MyApp.Companion.url}")


                if (MyApp.Companion.url.isNotEmpty()) {
                    val intent = Intent(context, WebViewActivity::class.java)
                    intent.putExtra("url", MyApp.Companion.url)
                    context.startActivity(intent)
                }
            }

        }
    }

    fun fetchAndShow(context: Context, key: String, onDone: () -> Unit,onFail : ()-> Unit) {
        remoteConfig.fetchAndActivate().addOnCompleteListener {

            if (it.isSuccessful) {

                MyApp.Companion.url = remoteConfig.getString(key)
                onDone.invoke()
            }else{
                onFail.invoke()
            }

        }
    }

}