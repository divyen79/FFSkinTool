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

        Log.d("TAG", "fetchAndShow: ${MyApp.Companion.url}")
        if (!isInternetAvailable(context)) {

            Log.d("TAG", "No Internet → Direct next screen")

            // 👉 Direct next screen
            function.invoke()
            return
        }

        // ✅ 2. Internet available → normal flow
        function.invoke()

        if (MyApp.Companion.url.isNotEmpty()) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", MyApp.Companion.url)
            context.startActivity(intent)
        }


//        remoteConfig.fetchAndActivate().addOnCompleteListener {
//
//
//
//            if (it.isSuccessful) {
//
//                MyApp.Companion.url = remoteConfig.getString(key)
//                Log.d("TAG", "fetchAndShow: ${MyApp.Companion.url}")
//
//
////                if (MyApp.Companion.url.isNotEmpty()) {
////                    val intent = Intent(context, WebViewActivity::class.java)
////                    intent.putExtra("url", MyApp.Companion.url)
////                    context.startActivity(intent)
////                }
//            }
//            else{
//
//            }
//
//        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return activeNetwork.hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI) ||
                activeNetwork.hasTransport(android.net.NetworkCapabilities.TRANSPORT_CELLULAR) ||
                activeNetwork.hasTransport(android.net.NetworkCapabilities.TRANSPORT_ETHERNET)
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