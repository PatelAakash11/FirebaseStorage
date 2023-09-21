package com.example.firebasestorageexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasestorageexample.data.DataRealTimeDatabase
import com.example.firebasestorageexample.databinding.RcvItemRealtimeDatabaseBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.database.core.Context

class AdapterRealTimeDatabase(
    private val context: android.content.Context,
    private val list: ArrayList<DataRealTimeDatabase>,
    private val onClickDeleteData: (selectedData: DataRealTimeDatabase) -> Unit
) : RecyclerView.Adapter<AdapterRealTimeDatabase.ItemViewHolder>() {

    class ItemViewHolder(val binding: RcvItemRealtimeDatabaseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(RcvItemRealtimeDatabaseBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.tvName.text = list.get(position).name
        holder.binding.tvFilePath.text = list.get(position).filePath
        holder.binding.tvFilePath.isSelected = true

        holder.binding.ivDelete.setOnClickListener {
            onClickDeleteData.invoke(list.get(position))
        }
    }
}