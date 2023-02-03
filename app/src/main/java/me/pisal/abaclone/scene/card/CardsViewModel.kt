package me.pisal.abaclone.scene.card

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.pisal.abaclone.model.entity.Account
import me.pisal.abaclone.model.entity.Card

class CardsViewModel : ViewModel() {

    private val _cards = MutableLiveData<List<Card>>()



}