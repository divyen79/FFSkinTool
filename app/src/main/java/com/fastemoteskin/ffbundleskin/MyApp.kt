package com.fastemoteskin.ffbundleskin

import android.app.Application
import com.google.firebase.FirebaseApp

class MyApp : Application() {

    companion object {
        var url: String = ""
    }

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }


}