package me.pisal.abaclone.scene.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.pisal.abaclone.R
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.databinding.FragmentTransfersBinding
import me.pisal.abaclone.scene.*
import me.pisal.abaclone.scene.payment.TrxChannelAdapter
import org.koin.android.ext.android.inject

class TransfersFragment : BaseFragment() {
    private val viewModel by inject<TransfersViewModel>()
    private lateinit var binding: FragmentTransfersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTransfersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setToolbarTitle(getString(R.string.transfers))
            safeRunOnUiThread(100) {
                setNavigationBackgroundColor(R.color.white)
            }
            mainViewModel.authenticated.observe(viewLifecycleOwner) {
                if (it) {
                    setupList()
                }
            }
        }
    }

    private fun setupList() {
        binding.rcl.adapter = TrxChannelAdapter().apply {
            viewModel.menus(true).observe(viewLifecycleOwner) {
                when (it) {
                    is TResult.Success -> {
                        it.data?.data?.let(::submitList)
                    }
                    else -> {}
                }
            }
        }
    }
}