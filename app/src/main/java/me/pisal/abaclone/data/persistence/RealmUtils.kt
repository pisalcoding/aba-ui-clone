package me.pisal.abaclone.data.persistence

import io.realm.kotlin.Realm
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.IDao
import me.pisal.abaclone.model.IDto

const val realmSchemaVersion: Long = 2

/**
 * Insert or update list of [IDto] into local realm database
 */
suspend inline fun <DTO : IDto<R>, reified DAO : IDao<DTO>, reified R : RealmObject> persistItems(
    dispatcher: CoroutineDispatcher = IO,
    realm: Realm,
    items: List<DTO>,
) {
    withContext(dispatcher) {
        realm.write {
            items.forEach { dto ->
                val dao = query(R::class, "id == $0", dto.id)
                    .first()
                    .find()
                if (dao != null && dao is DAO) {
                    dao.updateFromDto(dto)
                } else {
                    this.copyToRealm(dto.toDao())
                }
            }
        }
    }
}

/**
 * Insert or update a [IDto] into local realm database
 */
suspend fun <T> safeRealmCall(
    dispatcher: CoroutineDispatcher = IO,
    transaction: suspend () -> T,
): TResult<T> {
    return withContext(dispatcher) {
        try {
            TResult.Success(transaction.invoke())
        } catch (t: Throwable) {
            t.printStackTrace()
            TResult.Failure(t.message, null)
        }
    }
}

suspend inline fun <DTO : IDto<R>, reified DAO : IDao<DTO>, reified R : RealmObject> persistItem(
    dispatcher: CoroutineDispatcher = IO,
    realm: Realm,
    item: DTO,
) {
    withContext(dispatcher) {
        realm.write {
            val dao = query(R::class, "id == $0", item.id)
                .first()
                .find()
            if (dao != null && dao is DAO) {
                dao.updateFromDto(item)
            } else {
                this.copyToRealm(item.toDao())
            }
        }
    }
}


