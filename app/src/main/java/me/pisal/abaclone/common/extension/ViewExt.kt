package me.pisal.abaclone.common.extension

import android.view.View

fun View.hide(){
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}