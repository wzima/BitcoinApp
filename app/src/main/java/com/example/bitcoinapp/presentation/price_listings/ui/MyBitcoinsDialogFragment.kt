package com.example.bitcoinapp.presentation.price_listings.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.bitcoinapp.R


class MyBitcoinsDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.MyBitcoins))
            .setPositiveButton(getString(R.string.ok)) { _, _ -> }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            .create()

    companion object {
        const val TAG = "MyBitcoinsDialogFragment"
    }
}
