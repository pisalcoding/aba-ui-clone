package me.pisal.abaclone.scene.pin.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import me.pisal.abaclone.R
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.common.extension.shake
import me.pisal.abaclone.databinding.FragmentPinAuthBinding
import me.pisal.abaclone.scene.hideActionBar
import me.pisal.abaclone.scene.hideBlurIfNotLoading
import me.pisal.abaclone.scene.setNavigationBackgroundColor
import me.pisal.abaclone.scene.withMainActivity
import me.pisal.alerter.Alerter

class PinAuthFragment : Fragment() {

    private val viewModel by viewModels<PinAuthViewModel>()
    private lateinit var binding: FragmentPinAuthBinding

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
            hideActionBar()
            setNavigationBackgroundColor(R.color.app_primary)
            mainViewModel.authenticated.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigateUp()
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
                findNavController().popBackStack(R.id.nav_home, false)
            }

            pinAuthView.onFilled = { pin ->
                authenticate(pin)
            }
        }
    }

    private fun authenticate(pin: String) {
        viewModel.authenticate(pin).observe(viewLifecycleOwner) {
            when (it) {
                is TResult.Success -> {
                    withMainActivity {
                        mainViewModel.authStatusChanged(true)
                    }
                }

                is TResult.Failure -> {
                    Alerter.error()
                        .withTitle("Invalid PIN!")
                        .withMessage("Please try and make sure you inputted the correct PIN.")
                        .show(childFragmentManager, "PinAuthFragment")
                    binding.pinAuthView.clearInputs()
                }
            }
        }
    }
}