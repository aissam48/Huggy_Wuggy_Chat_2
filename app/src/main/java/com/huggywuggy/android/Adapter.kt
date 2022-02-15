package com.huggywuggy.android

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.layout_huggy_mesg.*
import kotlinx.android.synthetic.main.layout_your_mesg.*

class AdapterHuggy(val text: String) :Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.text_huggy.text = text

    }

    override fun getLayout(): Int =R.layout.layout_huggy_mesg
}


class AdapterYours(val text :String) :Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.text_yours.text = text
    }

    override fun getLayout(): Int =R.layout.layout_your_mesg
}