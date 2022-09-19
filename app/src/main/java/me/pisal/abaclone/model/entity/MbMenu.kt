package me.pisal.abaclone.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MbMenu(

	@field:SerializedName("cir")
	val usesCircularIcon: Int? = null,

	@field:SerializedName("tco")
	val terminalCode: String? = null,

	@field:SerializedName("ico")
	val icon: String? = null,

	@field:SerializedName("ttl")
	val title: String? = null,

	@field:SerializedName("ver")
	val version: Int? = null,

	@field:SerializedName("ena")
	val enabled: Int? = null,

	@field:SerializedName("ics")
	val highlightIcons: String? = null,

	@field:SerializedName("loc")
	val localDrawableId: String? = null,

	@field:SerializedName("stt")
	val subtitle: String? = null,

	@field:SerializedName("sco")
	val serviceCode: String? = null,

	@field:SerializedName("out")
	val needsIconOutline: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("aut")
	val requiresAuth: Int? = null,

	@field:SerializedName("sbt")
	val subButtonText: String? = null,

	@field:SerializedName("sta")
	val status: Int? = null
): Serializable