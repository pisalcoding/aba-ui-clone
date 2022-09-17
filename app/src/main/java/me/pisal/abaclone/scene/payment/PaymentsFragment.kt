package me.pisal.abaclone.scene.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.pisal.abaclone.R
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.databinding.FragmentPaymentsBinding
import me.pisal.abaclone.scene.BaseFragment
import me.pisal.abaclone.scene.setToolbarTitle
import me.pisal.abaclone.scene.withMainActivity
import org.koin.android.ext.android.inject

class PaymentsFragment : BaseFragment() {
    private val viewModel by inject<PaymentsViewModel>()
    private lateinit var binding: FragmentPaymentsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setToolbarTitle(getString(R.string.payments))
        }
    }

    private fun setupList() {
        binding.rcl.adapter = TrxChannelAdapter().apply {
            viewModel.menus().observe(viewLifecycleOwner) {
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