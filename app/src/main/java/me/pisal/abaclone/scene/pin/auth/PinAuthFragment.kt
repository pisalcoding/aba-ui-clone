package me.pisal.abaclone.scene.pin.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import me.pisal.abaclone.R
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.databinding.FragmentPinAuthBinding
import me.pisal.abaclone.scene.*

class PinAuthFragment : Fragment() {

    private val viewModel by viewModels<PinAuthViewModel>()
    private lateinit var binding: FragmentPinAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setStyle(STYLE_NO_FRAME, R.style.FullScreenTransparentDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPinAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            //            showBlur()
            hideActionBar()
            setNavigationBackgroundColor(R.color.app_primary)
            mainViewModel.authenticated.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigateUp()
                    // dismiss()
                }
            }
        }
        initListeners()
    }

    override fun onStop() {
        super.onStop()
        withMainActivity {
            hideBlurIfNotLoading()
        }
    }

    private fun initListeners() {
        with(binding) {
            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }

            pinAuthView.onFilled = { pin ->
                authenticate(pin)
            }
        }
    }

    private fun authenticate(pin: String) {
        viewModel.authenticate(pin).observe(viewLifecycleOwner) {
            when(it) {
                is TResult.Success -> {
                    withMainActivity {
                        mainViewModel.authStatusChanged(true)
                    }
                }
                is TResult.Failure -> {
                    Log.e("Error", it.message ?: "")
                }
            }
        }
    }
}