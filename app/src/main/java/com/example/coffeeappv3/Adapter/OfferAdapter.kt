package com.example.coffeeappv3.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeappv3.Model.itemsModel
import com.example.coffeeappv3.databinding.ViewholderOfferBinding

class OfferAdapter(val items:MutableList<itemsModel>):
    RecyclerView.Adapter<OfferAdapter.Viewholder>() {

        private var context: Context?=null
        class Viewholder (val binding: ViewholderOfferBinding):RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderOfferBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }



    override fun onBindViewHolder(holder: OfferAdapter.Viewholder, position: Int) {

        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "R" + items[position].price.toString()


        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener{

        }


    }

    override fun getItemCount(): Int = items.size


}