package com.emad.currency.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.currencyConvertedItem -> {
                findNavController().navigate(CurrencyFragmentDirections.actionCurrencyFragmentToCurrencyConverterFragment())
            }
            R.id.historicalItem ->{
                findNavController().navigate(CurrencyFragmentDirections.actionCurrencyFragmentToHistoricalDataFragment())
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}