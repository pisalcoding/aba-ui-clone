package me.pisal.abaclone.ui

import androidx.annotation.ColorRes
import me.pisal.abaclone.MainActivity

fun MainActivity.setNavigationBackgroundColor(@ColorRes colorResId: Int){
    window.navigationBarColor = resources.getColor(colorResId, theme)
}