package me.pisal.abaclone.scene

import android.content.Context
import androidx.lifecycle.*
import me.pisal.abaclone.data.repo.account.AccountsRepository
import me.pisal.abaclone.module.RepoQualifier
import org.koin.java.KoinJavaComponent.get

class MainViewModel : ViewModel() {

    private val accountsRepo = get<AccountsRepository>(AccountsRepository::class.java, RepoQualifier.Remote)

    private val _authenticated = MutableLiveData<Boolean>()
    val authenticated: LiveData<Boolean> = _authenticated
    fun authStatusChanged(authenticated: Boolean) {
        if (_authenticated.value != authenticated) {
            _authenticated.value = authenticated
        }
    }

    fun accounts(context: Context) = liveData {
        emit(accountsRepo.list())
    }
}