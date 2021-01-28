package com.example.spacenewsdemo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProviderMessage(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("provider")
    val provider: String? = null
): Parcelable