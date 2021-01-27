package com.example.spacenewsdemo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class News(
	@SerializedName("id") val id: String? = null,
	@SerializedName("title") val title: String? = null,
	@SerializedName("url") val url: String? = null,
	@SerializedName("imageUrl") val imageUrl: String? = null,
	@SerializedName("newsSite") val newsSite: String? = null,
	@SerializedName("summary") val summary: String? = null,
	@SerializedName("publishedAt") val publishedAt: String? = null,
	@SerializedName("updatedAt") val updatedAt: String? = null,
	@SerializedName("featured") val featured: Boolean? = null,
	@SerializedName("launches") val launches: List<ProviderMessage>? = null,
	@SerializedName("events") val events: List<ProviderMessage>? = null
): Serializable