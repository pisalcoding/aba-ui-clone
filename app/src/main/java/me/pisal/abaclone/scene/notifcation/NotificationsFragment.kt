package me.pisal.abaclone.scene.notifcation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import me.pisal.abaclone.databinding.FragmentNotificationsBinding
import me.pisal.abaclone.scene.BaseFragment
import org.koin.android.ext.android.inject

class NotificationsFragment : BaseFragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private val viewModel by inject<NotificationsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentNotificationsBinding.inflate(inflater, container, false).also {
            this@NotificationsFragment.binding = it
            setupTabLayout()
            return it.root
        }
    }

    private fun setupTabLayout() {
        binding.viewPager2.adapter = NotificationPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, index ->
            viewModel.titles(requireContext()).observe(viewLifecycleOwner) {
                tab.text = it[index]
            }
        }.attach()
    }
}