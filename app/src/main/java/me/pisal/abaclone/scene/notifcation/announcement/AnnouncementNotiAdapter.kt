package me.pisal.abaclone.scene.notifcation.announcement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.pisal.abaclone.databinding.ItemAnnouncementNotiBinding
import me.pisal.abaclone.model.dto.Notification
import me.pisal.abaclone.model.dto.NotificationDiff

class AnnouncementNotiAdapter: ListAdapter<Notification, AnnouncementNotiAdapter.ViewHolder>(NotificationDiff) {

    inner class ViewHolder(private val binding: ItemAnnouncementNotiBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bindData(item: Notification) {
                binding.item = item
                binding.executePendingBindings()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ItemAnnouncementNotiBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            return ViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}