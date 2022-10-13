package me.pisal.abaclone.scene

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.pisal.abaclone.R
import me.pisal.abaclone.scene.pin.auth.PinAuthFragment


open class BaseFragment(
    val sensitive: Boolean = true,
    val requireAuth: Boolean = true,
    val multitouch: Boolean = false
) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireAuth) {
            findNavController().navigate(R.id.toPinAuth)
        }
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            showActionBar()
        }
    }
}