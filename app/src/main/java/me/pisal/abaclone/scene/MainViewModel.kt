package me.pisal.abaclone.scene

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.account.AccountsRepository
import me.pisal.abaclone.di.RepoQualifier
import org.koin.java.KoinJavaComponent.get

class MainViewModel : ViewModel() {

    private val accountsRepo = get<AccountsRepository>(AccountsRepository::class.java, RepoQualifier.Local)

    private val _authenticated = MutableLiveData<Boolean>()
    val authenticated: LiveData<Boolean> = _authenticated
    fun authStatusChanged(authenticated: Boolean) {
        if (_authenticated.value != authenticated) {
            _authenticated.value = authenticated
        }
    }

    fun accounts() = liveData {
        emit(accountsRepo.list())
    }
}