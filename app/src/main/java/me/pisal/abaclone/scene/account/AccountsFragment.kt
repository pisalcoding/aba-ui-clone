package me.pisal.abaclone.scene.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import me.pisal.abaclone.R
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.databinding.FragmentAccountsBinding
import me.pisal.abaclone.scene.*

class AccountsFragment : BaseFragment() {

    private lateinit var binding: FragmentAccountsBinding
    private val viewModel by viewModels<AccountsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        withMainActivity {
            setToolbarTitle(getString(R.string.accounts))
            safeRunOnUiThread(100) {
                setNavigationBackgroundColor(R.color.white)
            }

            mainViewModel.authenticated.observe(viewLifecycleOwner) {
                if (it) {
                    setupList()
                    setupChart()
                }
            }
        }
    }

    private fun setupList() {
        viewModel.totalInKhr.observe(viewLifecycleOwner, binding::setTotalInKhr)
        viewModel.totalInUsd.observe(viewLifecycleOwner, binding::setTotalInUsd)

        withMainActivity {
            mainViewModel.accounts().observe(viewLifecycleOwner) {
                binding.rcl.adapter = AccountsAdapter().apply {
                    if (it is TResult.Success && it.data?.data != null) {
                        viewModel.accountsUpdated(it.data.data)
                        submitList(it.data.data)
                    }
                }
            }
        }
    }

    private fun setupChart() {
        val chart = binding.pieChart.apply {
            setDrawEntryLabels(false)
            setDrawCenterText(true)
            setHoleColor(ColorTemplate.colorWithAlpha(ColorTemplate.rgb("#00FFFFFF"), 0))
            setCenterTextColor(ColorTemplate.rgb("#FFFFFF"))
            setUsePercentValues(false)
            setTransparentCircleAlpha(0)
            isEnabled = false
            centerText = getString(me.pisal.abaclone.R.string.all_accounts_summary)
            isRotationEnabled = false
            isHighlightPerTapEnabled = false
            description = Description().apply { text = "" }

            legend.apply {
                isEnabled = false
            }
        }

        withMainActivity {
            mainViewModel.accounts().observe(viewLifecycleOwner) {
                when (it) {
                    is TResult.Success -> {
                        val accounts = it.data?.data ?: listOf()
                        val entryList: ArrayList<PieEntry> = ArrayList()
                        val colorList: ArrayList<Int> = ArrayList()
                        val range = 100f
                        for (i in accounts.indices) {
                            val value = ((Math.random().toFloat() * range) + range / 5f)
                            entryList.add(PieEntry(value, accounts[i % accounts.size]))
                            colorList.add(ColorTemplate.rgb(accounts[i].colorHex))
                        }

                        PieDataSet(entryList, "").apply {
                            setDrawIcons(false)
                            sliceSpace = 3f
                            iconsOffset = MPPointF(0f, 10f)
                            selectionShift = 0f
                            valueLineWidth = 8f
                            valueTextSize = 0f
                            colors = colorList
                        }.run {
                            val data = PieData(this)
                            data.setValueTextSize(0f)
                            chart.data = data
                            chart.invalidate()
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}