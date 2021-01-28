package com.example.spacenewsdemo.util

import android.os.Parcel
import android.os.Parcelable
import com.example.spacenewsdemo.network.ProviderMessage
import com.google.gson.annotations.SerializedName

// Using Parcelable instead of Serializable
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
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
		parcel.createTypedArrayList(ProviderMessage),
		parcel.createTypedArrayList(ProviderMessage)
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(id)
		parcel.writeString(title)
		parcel.writeString(url)
		parcel.writeString(imageUrl)
		parcel.writeString(newsSite)
		parcel.writeString(summary)
		parcel.writeString(publishedAt)
		parcel.writeString(updatedAt)
		parcel.writeValue(featured)
		parcel.writeTypedList(launches)
		parcel.writeTypedList(events)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<News> {
		override fun createFromParcel(parcel: Parcel): News {
			return News(parcel)
		}

		override fun newArray(size: Int): Array<News?> {
			return arrayOfNulls(size)
		}
	}
}