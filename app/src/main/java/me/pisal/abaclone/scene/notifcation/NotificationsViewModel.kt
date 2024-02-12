package me.pisal.abaclone.scene.notifcation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.R

class NotificationsViewModel: ViewModel() {

    fun titles(context: Context) = liveData {
        emit(listOf(
            context.getString(R.string.tab_transactions),
            context.getString(R.string.tab_announcements)
        ))
    }
}