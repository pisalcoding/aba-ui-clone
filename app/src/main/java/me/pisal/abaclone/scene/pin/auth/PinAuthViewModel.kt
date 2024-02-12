package me.pisal.abaclone.scene.pin.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.auth.AuthRepository
import me.pisal.abaclone.di.RepoQualifier
import org.koin.java.KoinJavaComponent.get

class PinAuthViewModel : ViewModel() {

    private val authRepo: AuthRepository = get(AuthRepository::class.java, RepoQualifier.Local)

    fun authenticate(pin: String) = liveData {
        emit(authRepo.authenticate(pin = pin))
    }
}