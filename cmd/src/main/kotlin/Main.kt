import su.mandora.mcskinlookup.MCSkinLookup
import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JOptionPane

const val UPSCALE = 16 // Skins are tiny, make them a bit more visible in the output

fun main(args: Array<String>) {
    val name = args.joinToString(" ")
    val lookup = MCSkinLookup()
    val skinData = lookup.lookupName(name)

    val conn = URL(skinData.textures.skin!!.url).openConnection()
    conn.connect()
    val image = ImageIO.read(conn.getInputStream())
    val upscale = image.getScaledInstance(image.width * UPSCALE, image.height * UPSCALE, BufferedImage.SCALE_FAST)
    JOptionPane.showMessageDialog(
        null,
        null,
        "Skin from $name",
        JOptionPane.INFORMATION_MESSAGE,
        ImageIcon(upscale))
}