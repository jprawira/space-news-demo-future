package com.example.spacenewsdemo.network

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

// Using Parcelable instead of Serializable
data class ProviderMessage(
    @SerializedName("id") val id: String? = null,
    @SerializedName("provider") val provider: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(provider)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProviderMessage> {
        override fun createFromParcel(parcel: Parcel): ProviderMessage {
            return ProviderMessage(parcel)
        }

        override fun newArray(size: Int): Array<ProviderMessage?> {
            return arrayOfNulls(size)
        }
    }
}