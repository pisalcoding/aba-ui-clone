package me.pisal.abaclone.common.extension

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import me.pisal.abaclone.R

fun View.hide(){
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.shake() {
    val shake = AnimationUtils.loadAnimation(this.context, R.anim.shake)
    this.startAnimation(shake)
    try {
        if (this is EditText) {
            this.requestFocus()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
