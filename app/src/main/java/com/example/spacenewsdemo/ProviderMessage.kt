package com.example.spacenewsdemo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProviderMessage(
    @SerializedName("id") val id: String? = null,
    @SerializedName("provider") val provider: String? = null
): Serializable