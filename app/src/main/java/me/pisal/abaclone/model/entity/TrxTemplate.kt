package me.pisal.abaclone.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TrxTemplate(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("ttl")
	val title: String,

	@field:SerializedName("fav")
	val favorite: Boolean = false,

	@field:SerializedName("sco")
	val serviceCode: String,

	/**
	 * Source account number
	 */
	@field:SerializedName("src")
	val sourceAcc: String,

	/**
	 * Could be
	 * - receiver's account: for fund transfer,
	 * - bill code: for bill-based payment,
	 * - consumer code: for consumer-based payment
	 */
	@field:SerializedName("dst")
	val destinationAcc: String,

	/**
	 * Could be
	 * - biller code: for third-party payment systems such as Bill24,
	 * - terminal code: for POS-based payment
	 */
	@field:SerializedName("tco")
	val terminalCode: String? = null,

	/**
	 * Could be
	 * - Benefactor's bank/mfi code: for local bank transfer
	 */
	@field:SerializedName("ben")
	val benefactorInstitution: String? = null,

	@field:SerializedName("ico")
	val icon: String? = null,

	/**
	 * Emv content for emv-based transaction such as QR payment
	 */
	val emv: String? = null,

	@field:SerializedName("ver")
	val version: Int? = null,

	@field:SerializedName("sta")
	val status: Int? = null
): Serializable