package com.example.firebasestorageexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasestorageexample.data.DataRealTimeDatabase
import com.example.firebasestorageexample.databinding.RcvItemFilesBinding

class AdapterImages(
    private val context: Context,
    private val listOfImage: ArrayList<DataRealTimeDatabase>
) :
    RecyclerView.Adapter<AdapterImages.ItemImageHolder>() {

    class ItemImageHolder(val binding: RcvItemFilesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemImageHolder {
        return ItemImageHolder(RcvItemFilesBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
        return listOfImage.size
    }

    override fun onBindViewHolder(holder: ItemImageHolder, position: Int) {

        Glide.with(context).load(listOfImage.get(position).filePath).into(holder.binding.ivImage)
    }
}