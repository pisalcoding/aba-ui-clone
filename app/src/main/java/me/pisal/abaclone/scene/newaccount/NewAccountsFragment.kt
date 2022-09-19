package me.pisal.abaclone.scene.newaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.pisal.abaclone.R
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.databinding.FragmentNewAccountsBinding
import me.pisal.abaclone.scene.*
import org.koin.android.ext.android.inject

class NewAccountsFragment : BaseFragment() {
    private val viewModel by inject<NewAccountsViewModel>()
    private lateinit var binding: FragmentNewAccountsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setToolbarTitle(getString(R.string.account_openning))
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
        binding.rcl.adapter = NewAccountAdapter().apply {
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