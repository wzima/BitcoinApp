package com.example.bitcoinapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.bitcoinapp.R
import com.example.bitcoinapp.databinding.DashboardFragmentBinding
import com.example.bitcoinapp.domain.model.BitcoinChartValues
import com.example.bitcoinapp.presentation.viewmodels.BitcoinChartValuesViewModel
import com.example.bitcoinapp.presentation.viewmodels.BitcoinPriceListingsViewModel
import com.example.bitcoinapp.util.TimeConverter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment : Fragment() {
    lateinit var binding: DashboardFragmentBinding
    val viewModel: BitcoinPriceListingsViewModel by activityViewModels()
    val chartViewModel: BitcoinChartValuesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardFragmentBinding.inflate(inflater, container, false)

        //observer for updating the chart
        chartViewModel.bitcoinChartValues.observe(viewLifecycleOwner) { bitcoinChartValues ->
            setDataToLineChart(bitcoinChartValues)

        }

        //observer for showing/udating my bitcoin value
        viewModel.myBitcoins.observe(viewLifecycleOwner)
        {
            updateMyBitcoinView(it)
        }
        viewModel.bitcoinToEuroRate.observe(viewLifecycleOwner) {
            binding.myBitcoinsInEuro = viewModel.convertBitcoinToEuro()
        }

        //observer for showing the progressbar while loading
        viewModel.isLoading.observe(viewLifecycleOwner)
        { isLoading ->
            Log.d("isLoading", isLoading.toString())
            binding.progressBar.visibility =
                if (isLoading) View.VISIBLE
                else View.GONE
        }


        //set the adapter for the recycle view of the price listing
        with(binding.recyclerView) {
            val myAdapter = BitcoinPriceAdapter()
            adapter = myAdapter
            // Add an observer on the LiveData
            viewModel.allBitcoinPrices.observe(viewLifecycleOwner) { listings ->
                listings.let { myAdapter.submitList(it) }
                Log.d("getPriceListings", listings.size.toString())
                listings.forEach {
                    Log.d("getPriceListings", it.currency + " " + it.price)
                }

            }

            //show a message when list ist empty
            setEmptyView(binding.tvEmpty)
        }


        //initialize the swipetorefresh. data is reloaded when swiped down
        binding.swipeToRefresh.isEnabled = true
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getBitcoinPriceListings()
            chartViewModel.getChartValues()

            binding.swipeToRefresh.isRefreshing = false
        }

        setHasOptionsMenu(true)

        setUpLineChart()

        return binding.root
    }

    private fun updateMyBitcoinView(value: Float) {
        binding.myBitcoins = value
        binding.myBitcoinsInEuro = viewModel.convertBitcoinToEuro()

        if (value == 0f) {
            binding.tvMyBitcoins.visibility = View.GONE
        } else {
            binding.tvMyBitcoins.visibility = View.VISIBLE
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.dashboard_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.addMyBitcoins -> openMyBitcoinsDialog()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun openMyBitcoinsDialog() {

        MyBitcoinsDialogFragment().show(
            childFragmentManager, MyBitcoinsDialogFragment.TAG
        )

    }

    private fun setUpLineChart() {
        with(binding.chart) {

            axisRight.isEnabled = false

            animateX(1000, Easing.EaseInSine)

            description.isEnabled = false

            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = MyAxisFormatter()
            xAxis.granularity = 1F
            xAxis.setDrawGridLines(true)
            xAxis.setDrawAxisLine(false)

            axisLeft.setDrawGridLines(true)
            extraRightOffset = 30f

            legend.isEnabled = true
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.form = Legend.LegendForm.LINE

        }
    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        //private var items = arrayListOf("Milk", "Butter", "Cheese", "Ice cream", "Milkshake")

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return TimeConverter.fromMillis(value.toLong())
        }
    }

    private fun setDataToLineChart(data: BitcoinChartValues) {

        val price = LineDataSet(data.xy, "Bitcoin/USD")
        price.lineWidth = 3f
        price.valueTextSize = 12f
        price.mode = LineDataSet.Mode.LINEAR
        price.color = ContextCompat.getColor(requireContext(), R.color.red)
        price.circleRadius = 1f


        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(price)

        val lineData = LineData(dataSet)
        binding.chart.data = lineData

        binding.chart.invalidate()
    }


}
