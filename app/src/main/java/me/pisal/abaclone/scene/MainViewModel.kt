package me.pisal.abaclone.scene

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _authenticated = MutableLiveData<Boolean>().apply { value = false }
    val authenticated: LiveData<Boolean> = _authenticated
    fun authStatusChanged(authenticated: Boolean) {
        _authenticated.value = authenticated
    }
}