package com.example.kjaga.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kjaga.R
import com.example.kjaga.data.food.Food
import com.example.kjaga.data.food.ItemsItem
import com.example.kjaga.databinding.ItemListBinding
import com.example.kjaga.databinding.ItemListSearchBinding

class SearchListAdapter(
    private val data: List<Food>
): RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemListSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = data[position]
        holder.binding.apply {
            searchTvFood.text = food.food.name
            searchTvKkal.text = food.portion.name
            searchTvLemak.text = food.portion.nutrition.fatg.toString()
            searchTvKarbo.text = food.portion.nutrition.carbohydratesg.toString()
            searchTvProtein.text = food.portion.nutrition.proteing.toString()
            searchTvKalori.text = food.portion.nutrition.sugarg.toString()
        }
    }

    override fun getItemCount(): Int = data.size
}