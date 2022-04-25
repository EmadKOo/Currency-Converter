package com.emad.currency.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.emad.currency.databinding.FragmentCurrencyConverterBinding
import com.emad.currency.domain.model.Currency
import com.emad.currency.presentation.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConverterFragment : Fragment() {
    lateinit var binding: FragmentCurrencyConverterBinding
    lateinit var fromCurrencyAdapter: ArrayAdapter<Currency>
    lateinit var toCurrencyAdapter: ArrayAdapter<Currency>
    val currencyViewModel by activityViewModels<CurrencyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCurrencyConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
    }

    private fun initObservables(){
        currencyViewModel.loadCurrencyStateFlow.observe(viewLifecycleOwner, {
            fromCurrencyAdapter= ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,currencyViewModel.loadCurrencyStateFlow.value?.data?: mutableListOf())
            toCurrencyAdapter= ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,currencyViewModel.loadCurrencyStateFlow.value?.data?: mutableListOf())
            binding.baseSelector.setAdapter(fromCurrencyAdapter)
            binding.otherCurrencySelector.setAdapter(toCurrencyAdapter)
        })
    }
}