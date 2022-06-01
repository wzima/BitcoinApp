package com.example.bitcoinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bitcoinapp.databinding.ConverterFragmentBinding
import com.example.bitcoinapp.databinding.DashboardFragmentBinding


class ConverterFragment : Fragment() {
    lateinit var binding: ConverterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ConverterFragmentBinding.inflate(inflater, container, false)

        //val fragmentName = arguments?.getString("fragmentName")

        binding.fragmentName.setText(R.string.Converter)

        return binding.root
    }

}
