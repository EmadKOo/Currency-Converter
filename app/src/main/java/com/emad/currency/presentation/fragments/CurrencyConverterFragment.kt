package com.emad.currency.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.emad.currency.databinding.FragmentCurrencyConverterBinding
import com.emad.currency.domain.model.Currency
import com.emad.currency.presentation.viewmodel.CurrencyViewModel
import com.emad.currency.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "CurrencyConverterFragmewq"
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
        initViews()
    }

    private fun initObservables(){
        currencyViewModel.loadCurrencyStateFlow.observe(viewLifecycleOwner, {
            fromCurrencyAdapter= ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,currencyViewModel.loadCurrencyStateFlow.value?.data?: mutableListOf())
            toCurrencyAdapter= ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,currencyViewModel.loadCurrencyStateFlow.value?.data?: mutableListOf())

            with(binding){
                baseSelector.setAdapter(fromCurrencyAdapter)
                otherCurrencySelector.setAdapter(toCurrencyAdapter)

                baseSelector.setOnItemClickListener { adapterView, view, i, l ->
                    currencyViewModel.setFromCurrency(currencyViewModel.loadCurrencyStateFlow.value?.data?.get(i)?: Currency())
                }
                otherCurrencySelector.setOnItemClickListener { adapterView, view, i, l ->
                    currencyViewModel.setToCurrency(currencyViewModel.loadCurrencyStateFlow.value?.data?.get(i)?:Currency())
                }
            }
        })

        currencyViewModel.convertCurrencyLiveData.observe(viewLifecycleOwner, {
            when(it){
                is Resource.Error -> {
                    Log.d(TAG, "initObservables: ERROR " + it.data)
                }
                is Resource.Loading -> {
                    Log.d(TAG, "initObservables: Loading")
                }
                is Resource.Success -> {
                    Log.d(TAG, "initObservables: SUCCESS " + it.data)
                    binding.convertedET.setText(it.data?.toString()?:"")
                }
            }
        })
    }

    private fun initViews(){
        with(binding){
            convertBtn.setOnClickListener {
                currencyViewModel.convertCurrency()
            }

            amountEt.doAfterTextChanged {
                if (it.isNullOrEmpty())
                    currencyViewModel.setAmount(1f)
                else
                    currencyViewModel.setAmount(it.toString().toFloat())
            }
        }
    }
}

