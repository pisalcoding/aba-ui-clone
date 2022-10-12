package me.pisal.abaclone.model.entity

import com.google.gson.annotations.SerializedName
import me.pisal.abaclone.model.dao.IMbMenu
import java.io.Serializable

data class MbMenu(

	@field:SerializedName("cir")
	override var usesCircularIcon: Int? = null,

	@field:SerializedName("tco")
	override var terminalCode: String? = null,

	@field:SerializedName("ico")
	override var icon: String? = null,

	@field:SerializedName("ttl")
	override var title: String? = null,

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

	@field:SerializedName("id")
	override var id: Int? = null,

	@field:SerializedName("aut")
	override var requiresAuth: Int? = null,

	@field:SerializedName("sbt")
	override var subButtonText: String? = null,

	@field:SerializedName("sta")
	override var status: Int? = null
): Serializable, IMbMenu