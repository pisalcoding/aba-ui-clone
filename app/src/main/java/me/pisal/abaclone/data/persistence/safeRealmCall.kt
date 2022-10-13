package me.pisal.abaclone.data.persistence

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import me.pisal.abaclone.common.TResult

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