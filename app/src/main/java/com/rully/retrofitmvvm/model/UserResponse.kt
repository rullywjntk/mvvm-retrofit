package com.rully.retrofitmvvm.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("meta")
	val meta: Meta
)

data class DataItem(

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("status")
	val status: String
)

data class Pagination(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("pages")
	val pages: Int,

	@field:SerializedName("limit")
	val limit: Int,

	@field:SerializedName("links")
	val links: Links,

	@field:SerializedName("page")
	val page: Int
)

data class Links(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("current")
	val current: String,

	@field:SerializedName("previous")
	val previous: Any
)

data class Meta(

	@field:SerializedName("pagination")
	val pagination: Pagination
)
