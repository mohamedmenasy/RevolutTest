package com.mohamedmenasy.core.extension

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

//set view to be visible
fun View.visible() {
  this.visibility = View.VISIBLE
}

//set view to be gone
fun View.invisible() {
  this.visibility = View.GONE
}

fun ImageView.loadFromUrl(url: String) =
    GlideToVectorYou.init()
        .with(this.context.applicationContext)
        .load(Uri.parse(url), this)
