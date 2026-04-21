package com.fastemoteskin.ffbundleskin.Activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.fastemoteskin.ffbundleskin.Adapter.OnboardingAdapter
import com.fastemoteskin.ffbundleskin.Model.OnboardingModel
import com.fastemoteskin.ffbundleskin.R
import com.fastemoteskin.ffbundleskin.RemoteConfigManager

class activity_onboarding : AppCompatActivity() {


    lateinit var viewPager: ViewPager2
    lateinit var btnNext: Button

    //    lateinit var dotsLayout: LinearLayout
    var shouldMoveNext = false

    private lateinit var adapter: OnboardingAdapter
    private val list = ArrayList<OnboardingModel>()


    private val NOTIFICATION_PERMISSION_CODE = 1001

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_CODE
                )
            }
        }
    }

    private val settingsLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // 👇 User settings mathi pacho aave tyare aa call thase

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val granted = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

                if (granted) {
                    // ✅ Permission allow thai gayu
                    shouldMoveNext = true

                    RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {}
                }
            }
        }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                shouldMoveNext = true   // ✅ mark કરો

                RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                    // ❌ અહીં કઈ નથી કરવાનું
                }
            } else {
                // Permission denied
                AlertDialog.Builder(this)
                    .setTitle("Permission Required")
                    .setMessage("Allow notification permission for better experience")
                    .setPositiveButton("Open Settings") { _, _ ->
                        val intent = Intent(
                            android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        )
                        val uri = android.net.Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        settingsLauncher.launch(intent)
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }


        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


                )

        // ✅ White status bar icons
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

        viewPager = findViewById(R.id.viewPager)
        btnNext = findViewById(R.id.btnNext)
//        dotsLayout = findViewById(R.id.dotsLayout)

        // Data
        list.add(
            OnboardingModel(
                "Stylish Characters",
                "Dive into the world of FF characters and discover what makes each one unique!",
                R.drawable.img1
            )
        )

        list.add(
            OnboardingModel(
                "Ultimate Pet Zone",
                "Unlock, upgrade, and dominate with your perfect FF pet!",
                R.drawable.img2
            )
        )

        list.add(
            OnboardingModel(
                "Next-Level Vehicles",
                "Speed into action with vehicles built for glory and survival!",
                R.drawable.img3
            )
        )

        list.add(
            OnboardingModel(
                "All Emotes",
                "Unlock every emote to express yourself in style, from epic wins to funny moments!",
                R.drawable.img4
            )
        )

        list.add(
            OnboardingModel(
                "Wheel of Rewards",
                "Spin the wheel and claim awesome in-game loot!",
                R.drawable.img5
            )
        )

        adapter = OnboardingAdapter(list)
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false
//        setupDots(0)
        viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                // setupDots(position)

                // ✅ ADD THIS LOGIC
                if (position == list.size - 1) {
                    btnNext.text = "Get Started"
                } else {
                    btnNext.text = "Next"
                }
            }
        })
        btnNext.setOnClickListener {

            if (viewPager.currentItem < list.size - 1) {
                if (viewPager.currentItem == 0) {
                    requestNotificationPermission()
                } else {
                    shouldMoveNext = true   // ✅ mark કરો

                    RemoteConfigManager.fetchAndShow(this, "fbundlwebid") {
                        // ❌ અહીં કઈ નથી કરવાનું
                    }
                }

            } else {
                openActivityStart(MainActivity::class.java.name)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (shouldMoveNext) {
            shouldMoveNext = false

            viewPager.setCurrentItem(viewPager.currentItem + 1, true)
        }
    }
//    private fun setupDots(position: Int) {
//        dotsLayout.removeAllViews()
//
//        for (i in list.indices) {
//            val dot = View(this)
//            val params = LinearLayout.LayoutParams(20, 8)
//            params.marginEnd = 8
//            dot.layoutParams = params
//
//            dot.setBackgroundResource(
//                if (i == position) R.drawable.dot_active
//                else R.drawable.dot_inactive
//            )
//
//            dotsLayout.addView(dot)
//        }
//
//    }
}