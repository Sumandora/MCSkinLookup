package su.mandora.mcskinlookup.model

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import su.mandora.mcskinlookup.model.impl.Error

val deserializer = Json { ignoreUnknownKeys = true }

inline fun <reified T : MojangResponse> deserializeMojang(json: String): T {
    try {
        return deserializer.decodeFromString<T>(json)
    } catch (e: SerializationException) {
        // If this happened, then the generic-typed model is probably outdated
        try {
            val error = deserializer.decodeFromString<Error>(json)
            error(error.path + ": " + error.errorMessage)
        } catch (e2: SerializationException) {
            // If this happened, then the error model is probably outdated
            e.printStackTrace()
            e2.printStackTrace()
            error("Can't deserialize json object")
        }
    }
}