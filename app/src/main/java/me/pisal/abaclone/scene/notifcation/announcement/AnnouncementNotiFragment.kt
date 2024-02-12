package me.pisal.abaclone.scene.notifcation.announcement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.databinding.FragmentAnnouncementNotiBinding
import org.koin.android.ext.android.inject

class AnnouncementNotiFragment : Fragment() {

    private lateinit var binding: FragmentAnnouncementNotiBinding
    private val viewModel by inject<AnnouncementNotiViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentAnnouncementNotiBinding.inflate(inflater, container, false).also {
            this.binding = it
            setupList()
            return it.root
        }
    }

    private fun setupList() {
        binding.rcl.adapter = AnnouncementNotiAdapter().apply {
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