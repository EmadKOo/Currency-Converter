package com.emad.currency.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emad.currency.R
import com.emad.currency.databinding.FragmentHistoricalDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoricalDataFragment : Fragment() {
    lateinit var binding: FragmentHistoricalDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentHistoricalDataBinding.inflate(inflater, container, false)
        return binding.root
    }


}