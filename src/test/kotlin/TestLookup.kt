import su.mandora.mcskinlookup.MCSkinLookup
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TestLookup {
    @Test
    fun testLookup() {
        val lookup = MCSkinLookup()

        val skinData = lookup.lookupName("Notch")
        assertEquals(skinData.textures.skin!!.isSlim(), false)
        assertEquals(skinData.textures.cape, null) // Lets hope he won't get a cape soon...
    }

    @Test
    fun testInvalidNames() {
        val lookup = MCSkinLookup()

        try {
            lookup.lookupName("_") // Crash here
            error("This shouldn't work")
        } catch (e: IllegalStateException) {
            // This branch is intended
            assertEquals(e.message, "Name does not exist")
        }
    }

    @Test
    fun testInvalidUUIDs() {
        val lookup = MCSkinLookup()

        try {
            lookup.lookupUUID("00000000-0000-0000-0000-000000000000") // Let's hope nobody manages to get this UUID
            error("This shouldn't work")
        } catch (e: IllegalStateException) {
            // This branch is intended
            assertEquals(e.message, "Json object was empty")
        }
    }
}
