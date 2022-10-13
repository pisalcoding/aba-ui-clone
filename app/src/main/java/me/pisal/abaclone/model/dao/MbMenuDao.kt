package me.pisal.abaclone.model.dao

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import me.pisal.abaclone.model.IMbMenu

class MbMenuDao : IMbMenu, RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()

    override var id: Int = 0
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
}