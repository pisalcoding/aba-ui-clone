package me.pisal.abaclone.scene.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.databinding.FragmentCardBinding
import me.pisal.abaclone.scene.*
import me.pisal.abaclone.scene.account.AccountsAdapter

class CardsFragment : BaseFragment() {

    private  lateinit var binding: FragmentCardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return  binding.root
    }


    private  fun setUpList() {

//        withMainActivity {
//            mainViewModel.accounts(requireContext()).observe(viewLifecycleOwner) {
//                binding.rclAddCard.adapter = AccountsAdapter().apply {
//                    if (it is TResult.Success && it.data?.data != null) {
//                        viewModel.accountsUpdated(it.data.data)
//                        submitList(it.data.data)
//                    }
//                }
//            }
//        }
    }
}