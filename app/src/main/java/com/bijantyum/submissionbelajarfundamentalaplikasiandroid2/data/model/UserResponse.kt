package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("items")
	val items: ArrayList<User>
)


