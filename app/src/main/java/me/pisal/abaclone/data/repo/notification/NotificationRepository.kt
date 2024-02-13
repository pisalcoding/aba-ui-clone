package me.pisal.abaclone.data.repo.notification

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.data.repo.FakerManager
import me.pisal.abaclone.model.dto.Notification
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.enumeration.NotificationType
import java.text.SimpleDateFormat


class NotificationRepository : INotificationRepository {

    override suspend fun list(type: NotificationType, page: Int, pageSize: Int): TResult<ResponseWrapper<List<Notification>>> {
        return withContext(Dispatchers.IO) {
            val list = arrayListOf<Notification>()
            val faker = FakerManager.getInstance()

            when (type) {
                NotificationType.TRANSACTION -> {
                    for (i in 0 until pageSize) {
                        list.add(
                            Notification(
                                id = "${type.name.take(3)}%03d".format(i),
                                title = faker.university.name(),
                                message = faker.lorem.paragraph(),
                                datetime = SimpleDateFormat("MMM dd, yyyy").format(faker.date.birthday(0, 1))
                            )
                        )
                    }
                }
                NotificationType.ANNOUNCEMENT -> {
                    for (i in 0 until pageSize) {
                        list.add(
                            Notification(
                                id = "${type.name.take(3)}%03d".format(i),
                                title = faker.university.name(),
                                message = faker.lorem.paragraph(),
                                datetime = SimpleDateFormat("MMM dd, yyyy").format(faker.date.birthday(0, 1)),
                                actionButton = faker.artist.name()
                            )
                        )
                    }
                }
            }

            TResult.Success(ResponseWrapper((list)))
        }
    }
}