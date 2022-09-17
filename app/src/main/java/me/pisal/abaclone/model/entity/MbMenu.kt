package me.pisal.abaclone.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MbMenu(

	@field:SerializedName("uses_circular_icon")
	val usesCircularIcon: Int? = null,

	@field:SerializedName("terminal_code")
	val terminalCode: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("version")
	val version: Int? = null,

	@field:SerializedName("enabled")
	val enabled: Int? = null,

	@field:SerializedName("highlight_icons")
	val highlightIcons: List<String>? = null,

	@field:SerializedName("local_drawable_id")
	val localDrawableId: String? = null,

	@field:SerializedName("subtitle")
	val subtitle: String? = null,

	@field:SerializedName("service_code")
	val serviceCode: String? = null,

	@field:SerializedName("needs_icon_outline")
	val needsIconOutline: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("requires_auth")
	val requiresAuth: Int? = null,

	@field:SerializedName("sub_button_text")
	val subButtonText: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
): Serializable