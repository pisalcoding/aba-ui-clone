package me.pisal.abaclone.scene.notifcation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.notification.INotificationRepository
import me.pisal.abaclone.model.enumeration.NotificationType

class TransactionNotiViewModel(
    private val repo: INotificationRepository
) : ViewModel() {

    fun list() = liveData {
        emit(repo.list(NotificationType.TRANSACTION, 1, 10))
    }

}