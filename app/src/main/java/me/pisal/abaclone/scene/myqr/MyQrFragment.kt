package me.pisal.abaclone.scene.myqr

import androidx.fragment.app.DialogFragment
import me.pisal.abaclone.R
import me.pisal.abaclone.scene.setNavigationBackgroundColor
import me.pisal.abaclone.scene.setStatusBarColor
import me.pisal.abaclone.scene.withMainActivity

class MyQrFragment : DialogFragment(R.layout.fragment_my_qr) {

    override fun getTheme(): Int {
        return R.style.DialogFragmentStyle_FullScreen
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setNavigationBackgroundColor(R.color.black)
            setStatusBarColor(R.color.black)
        }
    }
}