package com.example.bitcoinapp.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcoinapp.databinding.PriceItemViewBinding
import com.example.bitcoinapp.domain.model.BitcoinPriceListing

class BitcoinPriceAdapter() :
    ListAdapter<BitcoinPriceListing, BitcoinPriceAdapter.BitcoinPriceViewHolder>(COMPARATOR) {

    private lateinit var binding: PriceItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitcoinPriceViewHolder {
        binding = PriceItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BitcoinPriceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BitcoinPriceViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

    }

    class BitcoinPriceViewHolder(private val binding: PriceItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(bitcoinPriceListing: BitcoinPriceListing) {
            binding.bitcoinPriceListing = bitcoinPriceListing

        }

    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<BitcoinPriceListing>() {
            override fun areItemsTheSame(
                oldItem: BitcoinPriceListing,
                newItem: BitcoinPriceListing
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: BitcoinPriceListing,
                newItem: BitcoinPriceListing
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}

