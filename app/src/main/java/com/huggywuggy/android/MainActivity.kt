package com.huggywuggy.android

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.mlkit.nl.smartreply.SmartReply
import com.google.mlkit.nl.smartreply.SmartReplySuggestionResult
import com.google.mlkit.nl.smartreply.TextMessage
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var section: Section
    lateinit var conversation : ArrayList<TextMessage>
    lateinit var my_ad_banner_:AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation  =ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        conversation = ArrayList()

        MobileAds.initialize(this)
        my_ad_banner_ = findViewById<AdView>(R.id.my_ad_banner)

        val requestAd = AdRequest.Builder().build()
        my_ad_banner_.loadAd(requestAd)



        section = Section()
        var group = GroupAdapter<GroupieViewHolder>()
        group.add(section)

        recy.adapter = group

        val smartReplyGenerator = SmartReply.getClient()

        btn_send_msg.setOnClickListener {

            if (fill_text.text.isEmpty()){
                Toast.makeText(this, "Tap a message",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            val msg_yours = fill_text.text.toString()
            val item_yours = AdapterYours(msg_yours)
            section.add(0, item_yours)
            group.notifyDataSetChanged()



            conversation.add(TextMessage.createForLocalUser(
                msg_yours, System.currentTimeMillis()))


            smartReplyGenerator.suggestReplies(conversation)
                .addOnSuccessListener { result ->
                    if (result.getStatus() == SmartReplySuggestionResult.STATUS_NOT_SUPPORTED_LANGUAGE) {

                        Toast.makeText(this, "Only English",Toast.LENGTH_LONG).show()

                    } else if (result.getStatus() == SmartReplySuggestionResult.STATUS_SUCCESS) {

                        val msg_huggy = result.suggestions

                        for(i in msg_huggy){
                            val item_huggy = AdapterHuggy(i.text)
                            section.add(0, item_huggy)
                            group.notifyDataSetChanged()
                        }


                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "error",Toast.LENGTH_LONG).show()

                }

            fill_text.text.clear()

        }


    }
}


