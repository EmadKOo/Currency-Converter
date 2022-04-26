package com.emad.currency.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.emad.currency.R
import com.emad.currency.databinding.FragmentHistoricalDataBinding
import com.emad.currency.presentation.adapters.HistoricalAdapter
import com.emad.currency.presentation.viewmodel.CurrencyViewModel
import com.emad.currency.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoricalDataFragment : Fragment() {
    lateinit var binding: FragmentHistoricalDataBinding
    val currencyViewModel by activityViewModels<CurrencyViewModel>()
    @Inject
    lateinit var historicalAdapter: HistoricalAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentHistoricalDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currencyViewModel.loadHistoricalCurrency()
        initObservables()
    }


    private fun initObservables(){
        currencyViewModel.historicalLiveData.observe(viewLifecycleOwner, {
            when(it){
                is Resource.Error -> {
                    Log.d(TAG, "initObservables: ERROR " + it.data)
                }
                is Resource.Loading -> {
                    Log.d(TAG, "initObservables: Loading")
                }
                is Resource.Success -> {
                    Log.d(TAG, "initObservables: SUCCESS " + it.data)
                    binding.historicalRecyclerView.adapter= historicalAdapter
                    historicalAdapter.submitList(it.data?: mutableListOf())
                }
            }
        })
    }
}

private const val TAG = "HistoricalDataFragment"