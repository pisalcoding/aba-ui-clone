package me.pisal.abaclone.scene.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.pisal.abaclone.databinding.ItemTrxChannelMenuBinding
import me.pisal.abaclone.model.dto.MbMenuDto

class TrxChannelAdapter
    : ListAdapter<MbMenuDto, TrxChannelAdapter.TrxChannelViewHolder>(MbMenuDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrxChannelViewHolder {
        return ItemTrxChannelMenuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).run {
            TrxChannelViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: TrxChannelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TrxChannelViewHolder(private val binding: ItemTrxChannelMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MbMenuDto) {
            binding.item = data
            binding.executePendingBindings()
        }
    }
}

object MbMenuDiff : DiffUtil.ItemCallback<MbMenuDto>() {
    override fun areItemsTheSame(oldItem: MbMenuDto, newItem: MbMenuDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MbMenuDto, newItem: MbMenuDto): Boolean {
        return oldItem == newItem
    }
}