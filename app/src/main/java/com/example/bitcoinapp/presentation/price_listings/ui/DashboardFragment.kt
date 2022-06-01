package com.example.bitcoinapp.presentation.price_listings.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bitcoinapp.R
import com.example.bitcoinapp.databinding.DashboardFragmentBinding
import com.example.bitcoinapp.presentation.price_listings.BitcoinPriceListingsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment : Fragment() {
    lateinit var binding: DashboardFragmentBinding
    val viewModel: BitcoinPriceListingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardFragmentBinding.inflate(inflater, container, false)

        viewModel.isLoading.observe(viewLifecycleOwner)
        { isLoading ->
            Log.d("isLoading", isLoading.toString())
            binding.progressBar.visibility =
                if (isLoading) View.VISIBLE
                else View.GONE
        }

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


        binding.swipeToRefresh.isEnabled = true
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getBitcoinPriceListings()

            binding.swipeToRefresh.isRefreshing = false
        }

        setHasOptionsMenu(true)

        return binding.root
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

}
