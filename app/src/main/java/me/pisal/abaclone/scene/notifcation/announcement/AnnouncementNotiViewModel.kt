package me.pisal.abaclone.scene.notifcation.announcement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.notification.INotificationRepository
import me.pisal.abaclone.model.enumeration.NotificationType

class AnnouncementNotiViewModel(
    private val repo: INotificationRepository
): ViewModel() {

    fun list() = liveData {
        emit(repo.list(NotificationType.ANNOUNCEMENT, 1, 10))
    }
}