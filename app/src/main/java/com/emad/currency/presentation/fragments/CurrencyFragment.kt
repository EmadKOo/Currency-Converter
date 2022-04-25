package com.emad.currency.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.emad.currency.R
import com.emad.currency.databinding.FragmentCurrencyBinding
import com.emad.currency.presentation.adapters.CurrencyAdapter
import com.emad.currency.presentation.viewmodel.CurrencyViewModel
import com.emad.currency.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "CurrencyFragment"
@AndroidEntryPoint
class CurrencyFragment : Fragment() {
    lateinit var binding: FragmentCurrencyBinding
    val currencyViewModel by activityViewModels<CurrencyViewModel>()
    @Inject
    lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
    }

    private fun initObservables(){
        currencyViewModel.loadCurrencyStateFlow.observe(viewLifecycleOwner, {
            when(it){
                is Resource.Error -> {
                    Log.d(TAG, "onResume: ERROR " +it.data)
                }
                is Resource.Loading -> {
                    Log.d(TAG, "onResume: Loading")
                }
                is Resource.Success -> {
                    Log.d(TAG, "onResume: SUCCESS " + it.data)
                    binding.currencyRecyclerView.adapter= currencyAdapter
                    currencyAdapter.submitList(it.data ?: mutableListOf())
                }
            }
        })
    }
}