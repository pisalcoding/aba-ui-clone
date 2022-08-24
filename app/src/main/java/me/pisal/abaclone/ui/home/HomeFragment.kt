package me.pisal.abaclone.ui.home

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import me.pisal.abaclone.R
import me.pisal.abaclone.databinding.FragmentHomeBinding
import me.pisal.abaclone.ui.setNavigationBackgroundColor
import me.pisal.abaclone.ui.withMainActivity

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setNavigationBackgroundColor(R.color.app_accent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenus()
    }

    private fun setupMenus() {
        viewModel.gridMenus(requireContext()).observe(viewLifecycleOwner) {
            binding.gridMenus = it
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home, menu)
    }
}