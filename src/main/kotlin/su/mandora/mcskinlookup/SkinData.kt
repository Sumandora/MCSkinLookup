package su.mandora.mcskinlookup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Metadata(
    val model: String
)

@Serializable
data class Skin(
    val url: String,
    val metadata: Metadata? = null
) {
    fun isSlim() = metadata?.model == "slim"
}

@Serializable
data class Cape(
    val url: String
)

@Serializable
data class Textures(
    @SerialName("SKIN") val skin: Skin? = null /*Implies steve skin*/,
    @SerialName("CAPE") val cape: Cape? = null
)

@Serializable
data class SkinData(
    val timestamp: Long,
    val profileId: String,
    val profileName: String,
    val textures: Textures
)