package me.pisal.abaclone.data.repo.notification

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.data.repo.FakerManager
import me.pisal.abaclone.model.dto.Notification
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.enumeration.NotificationType


class NotificationRepository : INotificationRepository {

    override suspend fun list(type: NotificationType, page: Int, pageSize: Int): TResult<ResponseWrapper<List<Notification>>> {
        return withContext(Dispatchers.IO) {
            val list = arrayListOf<Notification>()
            val faker = FakerManager.getInstance()
            for (i in 0 until pageSize) {
                list.add(
                    Notification(
                        "${type.name.take(3)}%03d".format(i),
                        faker.university.name(),
                        faker.lorem.paragraph(),
                        faker.date.toString()
                    )
                )
            }
            TResult.Success(ResponseWrapper((list)))
        }
    }
}