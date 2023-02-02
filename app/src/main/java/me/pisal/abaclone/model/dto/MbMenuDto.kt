package me.pisal.abaclone.model.dto

import com.google.gson.annotations.SerializedName
import me.pisal.abaclone.model.IDto
import me.pisal.abaclone.model.IMbMenu
import me.pisal.abaclone.model.dao.MbMenuDao

data class MbMenuDto(

	@field:SerializedName("id")
	override var id: String,

	@field:SerializedName("ttl")
	override var title: String,

	@field:SerializedName("typ")
	override var type: String,

	@field:SerializedName("cir")
	override var usesCircularIcon: Int? = null,

	@field:SerializedName("tco")
	override var terminalCode: String? = null,

	@field:SerializedName("ico")
	override var icon: String? = null,

	@field:SerializedName("ver")
	override var version: Int? = null,

	@field:SerializedName("ena")
	override var enabled: Int? = null,

	@field:SerializedName("ics")
	override var highlightIcons: String? = null,

	@field:SerializedName("loc")
	override var localDrawableId: String? = null,

	@field:SerializedName("stt")
	override var subtitle: String? = null,

	@field:SerializedName("sco")
	override var serviceCode: String? = null,

	@field:SerializedName("out")
	override var needsIconOutline: Int? = null,

	@field:SerializedName("aut")
	override var requiresAuth: Int? = null,

	@field:SerializedName("sbt")
	override var subButtonText: String? = null,

	@field:SerializedName("sta")
	override var status: Int? = null,
) : IDto<MbMenuDao>, IMbMenu {

	override fun toDao(): MbMenuDao {
		return MbMenuDao().apply {
			this@apply.id = this@MbMenuDto.id
			this@apply.title = this@MbMenuDto.title
			this@apply.type = this@MbMenuDto.type
			this@apply.usesCircularIcon = this@MbMenuDto.usesCircularIcon
			this@apply.terminalCode = this@MbMenuDto.terminalCode
			this@apply.icon = this@MbMenuDto.icon
			this@apply.version = this@MbMenuDto.version
			this@apply.enabled = this@MbMenuDto.enabled
			this@apply.highlightIcons = this@MbMenuDto.highlightIcons
			this@apply.localDrawableId = this@MbMenuDto.localDrawableId
			this@apply.subtitle = this@MbMenuDto.subtitle
			this@apply.serviceCode = this@MbMenuDto.serviceCode
			this@apply.needsIconOutline = this@MbMenuDto.needsIconOutline
			this@apply.requiresAuth = this@MbMenuDto.requiresAuth
			this@apply.subButtonText = this@MbMenuDto.subButtonText
			this@apply.status = this@MbMenuDto.status
		}
	}

//	companion object {
//		fun fromDao(dao: MbMenuDao): MbMenuDto {
//			return MbMenuDto(
//				id = dao.id,
//				title = dao.title,
//				type = dao.type,
//				usesCircularIcon = dao.usesCircularIcon,
//				terminalCode = dao.terminalCode,
//				icon = dao.icon,
//				version = dao.version,
//				enabled = dao.enabled,
//				highlightIcons = dao.highlightIcons,
//				localDrawableId = dao.localDrawableId,
//				subtitle = dao.subtitle,
//				serviceCode = dao.serviceCode,
//				needsIconOutline = dao.needsIconOutline,
//				requiresAuth = dao.requiresAuth,
//				subButtonText = dao.subButtonText,
//				status = dao.status,
//			)
//		}
//	}
}