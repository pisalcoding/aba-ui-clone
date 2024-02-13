package me.pisal.abaclone.scene.myqr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import me.pisal.abaclone.R
import me.pisal.abaclone.databinding.FragmentMyQrBinding
import me.pisal.abaclone.scene.setNavigationBackgroundColor
import me.pisal.abaclone.scene.setStatusBarColor
import me.pisal.abaclone.scene.withMainActivity

class MyQrFragment : DialogFragment(R.layout.fragment_my_qr) {

    private lateinit var binding: FragmentMyQrBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentMyQrBinding.inflate(inflater, container, false).also {
            binding = it
            return it.root
        }
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}