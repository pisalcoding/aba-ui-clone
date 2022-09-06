package me.pisal.abaclone.scene.account

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupChart()
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
        withMainActivity {
            mainViewModel.accounts(requireContext()).observe(viewLifecycleOwner) {
                binding.rcl.adapter = AccountsAdapter().apply {
                    if (it is TResult.Success && it.data?.data != null) {
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
        }

        chart.legend.apply {
            isEnabled = false
            verticalAlignment = Legend.LegendVerticalAlignment.TOP
            horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            orientation = Legend.LegendOrientation.VERTICAL
            setDrawInside(false)
            xEntrySpace = 0f
            yEntrySpace = 0f
            yOffset = 0f
        }

        withMainActivity {
            mainViewModel.accounts(requireContext()).observe(viewLifecycleOwner) {
                val entries: ArrayList<PieEntry> = ArrayList()
                when (it) {
                    is TResult.Success -> {
                        val accounts = it.data?.data ?: listOf()
                        val colors: ArrayList<Int> = ArrayList()
                        for (i in accounts.indices) {
                            entries.add(
                                PieEntry(
                                    (Math.random().toFloat() * 10f + 10f / 5f),
                                    accounts[i % accounts.size]
                                )
                            )
                            colors.add(ColorTemplate.rgb(accounts[i].colorHex))
                        }

                        val dataSet = PieDataSet(entries, "").apply {
                            setDrawIcons(false)
                            sliceSpace = 3f
                            iconsOffset = MPPointF(0f, 10f)
                            selectionShift = 0f
                            valueLineWidth = 8f
                            valueTextSize = 0f
                        }

                        // add a lot of colors
                        colors.add(ColorTemplate.getHoloBlue())
                        dataSet.colors = colors

                        val data = PieData(dataSet)
                        data.setValueTextSize(0f)
                        chart.data = data

                        chart.invalidate()
                    }
                    else -> {}
                }
            }
        }
    }
}