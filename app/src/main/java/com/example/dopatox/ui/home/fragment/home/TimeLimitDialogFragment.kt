package com.example.dopatox.ui.home.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.example.dopatox.R
import com.example.dopatox.databinding.ScreenLimitDialogBinding
import com.example.dopatox.ui.utlis.successToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TimeLimitDialogFragment(val progress: RoundCornerProgressBar,val onDismiss: () -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: ScreenLimitDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenLimitDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTimeLimitRv()
    }

    private fun initTimeLimitRv() {
        val adapter = TimeLimitAdapter {
            successToast(requireContext(), getString(R.string.limit_set_to) + it.toString())
            progress.setMax(it)
            onDismiss()
            dismiss()
        }
        binding.timeLimitRv.adapter = adapter
        Log.d("Testo", "${adapter.itemCount}")
    }
}