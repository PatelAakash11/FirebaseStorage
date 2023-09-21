package com.example.firebasestorageexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasestorageexample.R
import com.example.firebasestorageexample.data.DataAudio
import com.example.firebasestorageexample.databinding.RcvItemsAudiosBinding

class AdapterAudio(
    private val context: Context,
    private val onClickSelectedAudio: (selectedFile: DataAudio) -> Unit
) :
    RecyclerView.Adapter<AdapterAudio.ItemViewHolder>() {

    private val listOFAudio = arrayListOf<DataAudio>()

    class ItemViewHolder(val binding: RcvItemsAudiosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(RcvItemsAudiosBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
        return listOFAudio.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        Glide.with(context).load(R.drawable.ic_audio).into(holder.binding.ivAudio)
        holder.binding.tvAudioName.text = listOFAudio.get(position).name

        holder.binding.crdAudio.setOnClickListener {
            onClickSelectedAudio.invoke(listOFAudio.get(position))
        }

    }

    fun setData(list: List<DataAudio>) {
        listOFAudio.clear()
        listOFAudio.addAll(list)
        notifyDataSetChanged()
    }
}