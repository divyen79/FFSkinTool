package com.fastemoteskin.ffbundleskin.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.fastemoteskin.ffbundleskin.MyApp
import com.fastemoteskin.ffbundleskin.RemoteConfigManager
import com.fastemoteskin.ffbundleskin.RemoteConfigManager.remoteConfig

fun Activity.openActivity(activityClass: String?) {
    val clazz = Class.forName(activityClass)
    val intent = Intent(this, clazz)
    startActivity(intent)
    finish()
}
fun Activity.openActivityStart(activityClass: String?) {

    if (!RemoteConfigManager.isInternetAvailable(this) || MyApp.Companion.url.isEmpty()) {

        // ✅ Direct open MainActivity
        openActivity(activityClass)

    }
    else {
        startActivity(
            Intent(this, WebViewActivity::class.java)
                .putExtra("ActivityName", activityClass)
                .putExtra("url", MyApp.Companion.url)
        )
    }


    finish()
}