package me.pisal.abaclone.scene.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import me.pisal.abaclone.R
import me.pisal.abaclone.databinding.FragmentHomeBinding
import me.pisal.abaclone.scene.*

@Suppress("DEPRECATION")
class HomeFragment : BaseFragment(sensitive = false, requireAuth = false) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onResume() {
        super.onResume()
        withMainActivity {
            hideBlurIfNotLoading()
            showActionBar()
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
        initListeners()
    }

    private fun setupMenus() {
        viewModel.gridMenus(requireContext()).observe(viewLifecycleOwner) {
            binding.gridMenus = it
        }
        setHasOptionsMenu(true)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home, menu)
    }

    private fun initListeners() {
        with(binding) {
            menuR1c1.root.setOnClickListener {
                 findNavController().navigate(R.id.fr_accounts)
            }
        }
    }
}