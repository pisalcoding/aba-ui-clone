package me.pisal.abaclone.data.repo.notification

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.Notification
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.enumeration.NotificationType

interface INotificationRepository {
    suspend fun list(type: NotificationType, page: Int, pageSize: Int): TResult<ResponseWrapper<List<Notification>>>
}