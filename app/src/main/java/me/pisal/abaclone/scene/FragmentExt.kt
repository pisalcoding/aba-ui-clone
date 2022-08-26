package me.pisal.abaclone.scene

import androidx.fragment.app.Fragment

fun Fragment.withMainActivity(block: MainActivity.() -> Unit) {
    if (this.activity != null && this.activity is MainActivity) {
        block(requireActivity() as @kotlin.ParameterName(name = "activity") MainActivity)
    }
}