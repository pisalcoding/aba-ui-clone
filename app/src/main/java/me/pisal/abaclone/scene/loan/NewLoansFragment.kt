package me.pisal.abaclone.scene.loan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.pisal.abaclone.R
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.databinding.FragmentNewLoansBinding
import me.pisal.abaclone.scene.*
import me.pisal.abaclone.scene.newaccount.NewAccountAdapter
import org.koin.android.ext.android.inject

class NewLoansFragment : BaseFragment() {
    private val viewModel by inject<NewLoansViewModel>()
    private lateinit var binding: FragmentNewLoansBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewLoansBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            safeRunOnUiThread(100) {
                setNavigationBackgroundColor(R.color.white)
            }
            setToolbarTitle(getString(R.string.loans))
            mainViewModel.authenticated.observe(viewLifecycleOwner) {
                if (it) {
                    setupList()
                }
            }
        }
    }

    private fun setupList() {
        binding.rcl.adapter = NewAccountAdapter().apply {
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