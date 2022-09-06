package me.pisal.abaclone.scene

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.model.data.account.AccountRepository
import org.koin.java.KoinJavaComponent.get

class MainViewModel : ViewModel() {

    private val accountsRepo = get<AccountRepository>(AccountRepository::class.java)

    private val _authenticated = MutableLiveData<Boolean>().apply { value = false }
    val authenticated: LiveData<Boolean> = _authenticated
    fun authStatusChanged(authenticated: Boolean) {
        _authenticated.value = authenticated
    }

    fun accounts(context: Context) = liveData {
        emit(accountsRepo.list())
    }
}