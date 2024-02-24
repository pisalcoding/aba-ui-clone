package me.pisal.abaclone.scene.template.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.pisal.abaclone.R
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.databinding.FragmentFavTransfersBinding
import me.pisal.abaclone.scene.BaseFragment
import me.pisal.abaclone.scene.setNavigationBackgroundColor
import me.pisal.abaclone.scene.setToolbarTitle
import me.pisal.abaclone.scene.template.TemplateListAdapter
import me.pisal.abaclone.scene.withMainActivity
import org.koin.android.ext.android.inject

class TransferTemplatesFragment : BaseFragment() {

    private lateinit var binding: FragmentFavTransfersBinding
    private val viewModel by inject<TransferTemplatesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentFavTransfersBinding.inflate(inflater, container, false).also {
            binding = it
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcl.adapter = TemplateListAdapter().apply {
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

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setToolbarTitle(getString(R.string.favorites))
            setNavigationBackgroundColor(R.color.white)
        }
    }
}