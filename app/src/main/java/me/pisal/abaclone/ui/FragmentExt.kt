package me.pisal.abaclone.ui

import androidx.fragment.app.Fragment
import me.pisal.abaclone.MainActivity

fun Fragment.withMainActivity(block: MainActivity.() -> Unit) {
    if (this.activity != null && this.activity is MainActivity) {
        block(requireActivity() as @kotlin.ParameterName(name = "activity") MainActivity)
    }
}