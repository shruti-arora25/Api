package com.example.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ListItemBinding

class RecyclerAdapter(private val List: ArrayList<Data>, context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {


    private lateinit var bind: ListItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        bind = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(bind)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = List[position]
        holder.apply {
            CountryName.text = currentItem.toString()
        }

    }

    override fun getItemCount(): Int {
        return List.size

    }


    class MyViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var CountryName = binding.name


    }
}
