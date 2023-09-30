# MCSkinLookup

MCSkinLookup is a simple library for looking up Minecraft player skins using the Mojang/Minecraft API.

## Usage

```kotlin
val lookup = MCSkinLookup()
val skinData = lookup.lookupName(name)

val conn = URL(skinData.textures.skin!!.url).openConnection()
conn.connect()

val image = ImageIO.read(conn.getInputStream())
```

This will look up the player skin for the provided username and download the skin texture.
