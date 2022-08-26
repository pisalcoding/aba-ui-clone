package me.pisal.abaclone.scene

import android.os.Bundle
import androidx.fragment.app.Fragment
import me.pisal.abaclone.scene.pin.auth.PinAuthFragment


open class BaseFragment(
    val sensitive: Boolean = true,
    val requireAuth: Boolean = true,
    val multitouch: Boolean = false
) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireAuth) {
            PinAuthFragment().show(childFragmentManager, "PinAuthFragment")
        }
    }
}