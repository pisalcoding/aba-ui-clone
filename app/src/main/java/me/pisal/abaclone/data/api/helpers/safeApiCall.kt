package me.pisal.abaclone.data.api.helpers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import me.pisal.abaclone.common.TResult
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

/**
 * Call to API with Error Handling
 */
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = IO,
    apiCall: suspend () -> T,
): TResult<T> {
    return withContext(dispatcher) {
        try {
            TResult.Success(apiCall.invoke())
        } catch (httpException: HttpException) {
            when (httpException.code()) {
                400 -> {
                    TResult.Failure(
                        message = httpException.localizedMessage,
                        code = "400"
                    )
                }
                else -> {
                    TResult.Failure(httpException.localizedMessage)
                }
            }
        }
        catch(exception: IOException) {
            TResult.Failure(exception.localizedMessage)
        }
        catch (exception: UnknownHostException){
            TResult.Failure(exception.localizedMessage)
        }
        catch (throwable: Throwable) {
            TResult.Failure(throwable.localizedMessage)
        }
    }
}