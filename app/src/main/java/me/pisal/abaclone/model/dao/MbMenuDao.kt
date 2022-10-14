package me.pisal.abaclone.model.dao

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import me.pisal.abaclone.model.IDao
import me.pisal.abaclone.model.IMbMenu
import me.pisal.abaclone.model.dto.MbMenuDto

class MbMenuDao : IMbMenu, IDao<MbMenuDto>, RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()

    override var id: String = ""
    override var title: String = ""
    override var type: String = ""
    override var usesCircularIcon: Int? = null
    override var terminalCode: String? = null
    override var icon: String? = null
    override var version: Int? = null
    override var enabled: Int? = null
    override var highlightIcons: String? = null
    override var localDrawableId: String? = null
    override var subtitle: String? = null
    override var serviceCode: String? = null
    override var needsIconOutline: Int? = null
    override var requiresAuth: Int? = null
    override var subButtonText: String? = null
    override var status: Int? = null

    override fun toDto(): MbMenuDto {
        return MbMenuDto(
            id,
            title,
            type,
            usesCircularIcon,
            terminalCode,
            icon,
            version,
            enabled,
            highlightIcons,
            localDrawableId,
            subtitle,
            serviceCode,
            needsIconOutline,
            requiresAuth,
            subButtonText,
            status,
        )
    }

    override fun updateFromDto(dto: MbMenuDto): IDao<MbMenuDto> {
        id = dto.id
        title = dto.title
        type = dto.type
        usesCircularIcon = dto.usesCircularIcon
        terminalCode = dto.terminalCode
        icon = dto.icon
        version = dto.version
        enabled = dto.enabled
        highlightIcons = dto.highlightIcons
        localDrawableId = dto.localDrawableId
        subtitle = dto.subtitle
        serviceCode = dto.serviceCode
        needsIconOutline = dto.needsIconOutline
        requiresAuth = dto.requiresAuth
        subButtonText = dto.subButtonText
        status = dto.status
        return this
    }
}