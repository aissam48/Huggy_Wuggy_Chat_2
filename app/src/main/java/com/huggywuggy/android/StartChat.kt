package com.huggywuggy.android

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_start_chat.*

class StartChat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_chat)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        MobileAds.initialize(this)
        var my_ad_banner_ = findViewById<AdView>(R.id.my_ad_banner_start)

        val requestAd = AdRequest.Builder().build()
        my_ad_banner_.loadAd(requestAd)

        btn_start_chat.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}