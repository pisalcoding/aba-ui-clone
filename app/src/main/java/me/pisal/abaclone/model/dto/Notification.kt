package me.pisal.abaclone.model.dto

import androidx.recyclerview.widget.DiffUtil

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val datetime: String?,
    val thumbnailUrl: String? = null,
    val link: String? = null,
    val actionButton: String? = null
)

object NotificationDiff : DiffUtil.ItemCallback<Notification>() {
    override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
        return oldItem.id == newItem.id && oldItem.title == newItem.title
    }

}
