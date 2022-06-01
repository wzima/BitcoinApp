package com.example.bitcoinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bitcoinapp.databinding.DashboardFragmentBinding
import com.example.bitcoinapp.databinding.MybitcoinsFragmentBinding


class MyBitcoinsFragment : Fragment() {
    lateinit var binding: MybitcoinsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MybitcoinsFragmentBinding.inflate(inflater, container, false)

        //val fragmentName = arguments?.getString("fragmentName")

        binding.fragmentName.setText(R.string.MyBitcoins)

        return binding.root
    }

}
