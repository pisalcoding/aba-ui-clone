package me.pisal.abaclone.model

interface IMbMenu: Identifiable {
    override var id: String
    var title: String
    var type: String
    var usesCircularIcon: Int?
    var terminalCode: String?
    var icon: String?
    var version: Int?
    var enabled: Int?
    var highlightIcons: String?
    var localDrawableId: Int?
    var subtitle: String?
    var serviceCode: String?
    var parentServiceCode: String?
    var needsIconOutline: Int?
    var requiresAuth: Int?
    var subButtonText: String?
    var status: Int?
}

enum class MenuType {
    PAYMENT,
    TRANSFER,
    NEW_ACCOUNT,
    LOANS,
    HOME
}