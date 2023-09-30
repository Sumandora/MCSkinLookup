package su.mandora.mcskinlookup.model.impl

import kotlinx.serialization.Serializable
import su.mandora.mcskinlookup.model.MojangResponse

@Serializable
data class Property(
    val name: String,
    val value: String
)

@Serializable
data class SkinLookup(
    val id: String,
    val name: String,
    val properties: List<Property>,
    val profileActions: List<String>
) : MojangResponse()
