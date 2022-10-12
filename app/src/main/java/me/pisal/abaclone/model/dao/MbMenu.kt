package me.pisal.abaclone.model.dao

import io.realm.kotlin.types.RealmObject

class MbMenu : IMbMenu, RealmObject {
    override var usesCircularIcon: Int? = null
    override var terminalCode: String? = null
    override var icon: String? = null
    override var title: String? = null
    override var version: Int? = null
    override var enabled: Int? = null
    override var highlightIcons: String? = null
    override var localDrawableId: String? = null
    override var subtitle: String? = null
    override var serviceCode: String? = null
    override var needsIconOutline: Int? = null
    override var id: Int? = null
    override var requiresAuth: Int? = null
    override var subButtonText: String? = null
    override var status: Int? = null
}

interface IMbMenu {
    var usesCircularIcon: Int?
    var terminalCode: String?
    var icon: String?
    var title: String?
    var version: Int?
    var enabled: Int?
    var highlightIcons: String?
    var localDrawableId: String?
    var subtitle: String?
    var serviceCode: String?
    var needsIconOutline: Int?
    var id: Int?
    var requiresAuth: Int?
    var subButtonText: String?
    var status: Int?
}