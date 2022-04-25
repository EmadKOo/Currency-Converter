package com.emad.currency.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emad.currency.databinding.CurrencyItemLayoutBinding
import com.emad.currency.domain.model.Currency
import javax.inject.Inject

class CurrencyAdapter @Inject constructor() :
    RecyclerView.Adapter<CurrencyAdapter.MyViewHolder>() {
    val list = ArrayList<Currency>()

    class MyViewHolder @Inject constructor(
        private val mBinding: CurrencyItemLayoutBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(currency: Currency) {
            mBinding.currency = currency
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CurrencyItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun submitList(newList: List<Currency>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}