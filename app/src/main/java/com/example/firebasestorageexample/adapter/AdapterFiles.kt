 package com.example.firebasestorageexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.firebasestorageexample.data.DataFiles
import com.example.firebasestorageexample.databinding.RcvItemFilesBinding

class AdapterFiles(
    private val context: Context,
    private val onClickSelectedFile: (selectedFile: DataFiles) -> Unit
) : Adapter<AdapterFiles.ItemViewHolder>() {

    private val listOfFiles = arrayListOf<DataFiles>()

    class ItemViewHolder(val binding: RcvItemFilesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(RcvItemFilesBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
        return listOfFiles.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        Glide.with(context).load(listOfFiles.get(position).file).into(holder.binding.ivImage)
        holder.binding.ivImage.setOnClickListener {
            onClickSelectedFile.invoke(listOfFiles.get(position))
        }
    }

    fun setData(list: List<DataFiles>) {
        listOfFiles.clear()
        listOfFiles.addAll(list)
        notifyDataSetChanged()
    }

}