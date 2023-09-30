package su.mandora.mcskinlookup

import kotlinx.serialization.SerializationException
import su.mandora.mcskinlookup.model.deserializeMojang
import su.mandora.mcskinlookup.model.deserializer
import su.mandora.mcskinlookup.model.impl.SkinLookup
import su.mandora.mcskinlookup.model.impl.UUIDLookup
import su.mandora.mcskinlookup.requests.JavaRequester
import su.mandora.mcskinlookup.requests.Requester
import java.io.FileNotFoundException
import java.util.*
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class MCSkinLookup(private val requester: Requester) {

    constructor() : this(JavaRequester())

    @OptIn(ExperimentalEncodingApi::class)
    fun decodeSkinData(encodedSkinData: String, uuid: Optional<String> = Optional.empty(), name: Optional<String> = Optional.empty()): SkinData {
        val skinData = try {
            deserializer.decodeFromString<SkinData>(Base64.decode(encodedSkinData).decodeToString())
        } catch (e: SerializationException) {
            e.printStackTrace()
            error("Can't decode skin data")
        }
        uuid.ifPresent { assert(skinData.profileId == it) }
        name.ifPresent { assert(skinData.profileName == it) }

        return skinData
    }

    fun lookupUUID(uuid: String, name: Optional<String> = Optional.empty()): SkinData {
        val skinLookup = deserializeMojang<SkinLookup>(try {
            requester.request(SKIN_LOOKUP.format(uuid))
        } catch (e: FileNotFoundException) {
            error("UUID does not exist")
        })
        assert(skinLookup.id == uuid)
        name.ifPresent { assert(skinLookup.name == it) }
        val textures = skinLookup.properties.firstOrNull { it.name == "textures" } ?: error("No skin data available")
        return decodeSkinData(textures.value, Optional.of(uuid), name)
    }

    fun lookupName(name: String): SkinData {
        val uuidLookup = deserializeMojang<UUIDLookup>(try {
            requester.request(UUID_LOOKUP.format(name))
        } catch (e: FileNotFoundException) {
            error("Name does not exist")
        })
        assert(uuidLookup.name == name)
        return lookupUUID(uuidLookup.id, Optional.of(uuidLookup.name))
    }

    companion object {
        const val UUID_LOOKUP = "https://api.mojang.com/users/profiles/minecraft/%s"
        const val SKIN_LOOKUP = "https://sessionserver.mojang.com/session/minecraft/profile/%s"
    }
}