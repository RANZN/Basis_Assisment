package com.ranzan.basisassisment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ranzan.basisassisment.R
import com.ranzan.basisassisment.databinding.ItemLayoutBinding
import com.ranzan.basisassisment.model.remote.ResponseModel

class CardAdapter(private val list: List<ResponseModel>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_layout, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int = list.size

    class CardViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(responseModel: ResponseModel) {
            binding.item = responseModel
        }
    }
}