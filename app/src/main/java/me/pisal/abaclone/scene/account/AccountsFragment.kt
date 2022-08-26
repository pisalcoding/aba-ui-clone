package me.pisal.abaclone.scene.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import me.pisal.abaclone.R
import me.pisal.abaclone.databinding.FragmentAccountsBinding
import me.pisal.abaclone.scene.*

class AccountsFragment : BaseFragment() {

    private lateinit var binding: FragmentAccountsBinding
    private val viewModel by viewModels<AccountsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setToolbarTitle("Accounts")
            safeRunOnUiThread(100) {
                setNavigationBackgroundColor(R.color.white)
            }
        }
    }

    private fun setupList() {
        viewModel.accounts().observe(viewLifecycleOwner) {
            binding.rcl.adapter = AccountsAdapter().apply {
                submitList(it)
            }
        }
    }
}