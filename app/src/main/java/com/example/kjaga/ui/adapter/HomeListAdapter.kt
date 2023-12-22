package com.example.kjaga.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kjaga.R
import com.example.kjaga.data.food.ItemsItem
import com.example.kjaga.databinding.ItemListBinding

class HomeListAdapter(
    private val data: List<ItemsItem?>?
): RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = data?.get(position)
        holder.binding.apply {
            imageView.setImageResource(R.drawable.logo)
            tvNameFood.text = food?.food?.name
            tvKkal.text=food?.nutrition?.energyKkal.toString()
        }
    }

    override fun getItemCount(): Int = data!!.size
}