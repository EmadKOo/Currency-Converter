package com.emad.currency.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emad.currency.databinding.CurrencyItemLayoutBinding
import com.emad.currency.databinding.HistoricalItemLayoutBinding
import com.emad.currency.domain.model.Currency
import com.emad.currency.domain.model.HistoricalCurrency
import javax.inject.Inject

class HistoricalAdapter @Inject constructor() :
    RecyclerView.Adapter<HistoricalAdapter.MyViewHolder>() {
    val list = ArrayList<HistoricalCurrency>()

    class MyViewHolder @Inject constructor(
        private val mBinding: HistoricalItemLayoutBinding
    ) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(currency: HistoricalCurrency) {
            mBinding.historicalItem= currency
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            HistoricalItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun submitList(newList: List<HistoricalCurrency>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}