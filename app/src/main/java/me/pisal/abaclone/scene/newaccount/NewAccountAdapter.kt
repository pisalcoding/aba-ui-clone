package me.pisal.abaclone.scene.newaccount

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.pisal.abaclone.databinding.ItemNewAccountMenuBinding
import me.pisal.abaclone.model.entity.MbMenu

class NewAccountAdapter
    : ListAdapter<MbMenu, NewAccountAdapter.TrxChannelViewHolder>(MbMenuDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrxChannelViewHolder {
        return ItemNewAccountMenuBinding.inflate(
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

    inner class TrxChannelViewHolder(private val binding: ItemNewAccountMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MbMenu) {
            binding.item = data
            binding.executePendingBindings()
        }
    }
}

object MbMenuDiff : DiffUtil.ItemCallback<MbMenu>() {
    override fun areItemsTheSame(oldItem: MbMenu, newItem: MbMenu): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MbMenu, newItem: MbMenu): Boolean {
        return oldItem == newItem
    }
}