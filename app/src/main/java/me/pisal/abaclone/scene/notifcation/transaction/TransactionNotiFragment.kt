package me.pisal.abaclone.scene.notifcation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.pisal.abaclone.R
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.databinding.FragmentTransactionNotiBinding
import me.pisal.abaclone.scene.safeRunOnUiThread
import me.pisal.abaclone.scene.setNavigationBackgroundColor
import me.pisal.abaclone.scene.setToolbarTitle
import me.pisal.abaclone.scene.withMainActivity
import org.koin.android.ext.android.inject

class TransactionNotiFragment : Fragment() {


    private lateinit var binding: FragmentTransactionNotiBinding
    private val viewModel by inject<TransactionNotiViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentTransactionNotiBinding.inflate(inflater, container, false).also {
            this.binding = it
            return it.root
        }
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setToolbarTitle(getString(R.string.payments))
            safeRunOnUiThread(100) {
                setNavigationBackgroundColor(R.color.white)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun setupList() {
        binding.rcl.adapter = TransactionNotiAdapter().apply {
            viewModel.list().observe(viewLifecycleOwner) {
                when (it) {
                    is TResult.Success -> {
                        if (!it.data?.data.isNullOrEmpty()) {
                            submitList(it.data!!.data)
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}