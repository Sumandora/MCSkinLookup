package su.mandora.mcskinlookup.model.impl

import kotlinx.serialization.Serializable
import su.mandora.mcskinlookup.model.MojangResponse

@Serializable
data class UUIDLookup(
    val name: String,
    val id: String
) : MojangResponse()
