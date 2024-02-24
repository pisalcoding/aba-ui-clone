package me.pisal.abaclone.scene.template.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.template.ITemplateRepository
import me.pisal.abaclone.model.enumeration.TemplateType

class TransferTemplatesViewModel(private val repo: ITemplateRepository) : ViewModel() {

    fun list() = liveData {
        emit(repo.list(TemplateType.TRANSFER))
    }
}