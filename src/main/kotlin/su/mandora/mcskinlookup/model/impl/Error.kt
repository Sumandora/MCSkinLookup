package su.mandora.mcskinlookup.model.impl

import kotlinx.serialization.Serializable
import su.mandora.mcskinlookup.model.MojangResponse

@Serializable
data class Error(
    val path: String,
    val errorMessage: String
) : MojangResponse()