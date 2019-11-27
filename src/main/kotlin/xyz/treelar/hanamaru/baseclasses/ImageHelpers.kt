package xyz.treelar.hanamaru.baseclasses

import com.jagrosh.jdautilities.command.CommandEvent
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URI
import java.net.URL
import java.util.regex.Pattern
import javax.imageio.ImageIO

fun Image.toBufferedImage(): BufferedImage {
    if (this is BufferedImage) {
        return this
    }
    val bufferedImage = BufferedImage(this.getWidth(null), this.getHeight(null), BufferedImage.TYPE_INT_ARGB)

    val graphics2D = bufferedImage.createGraphics()
    graphics2D.drawImage(this, 0, 0, null)
    graphics2D.dispose()

    return bufferedImage
}

fun BufferedImage.toPngInputStream(): InputStream {
    val os = ByteArrayOutputStream()
    ImageIO.write(this, "png", os)
    return ByteArrayInputStream(os.toByteArray())
}

fun CommandEvent.getImage(): BufferedImage? {
    val imageUrlPattern = Pattern.compile("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)")
    val matcher = imageUrlPattern.matcher(this.args)
    return when {
        this.message.attachments.getOrNull(0)?.isImage == true -> {
            ImageIO.read(this.message.attachments[0].retrieveInputStream().get())
        }
        matcher.find() -> {
            ImageIO.read(URL(matcher.group(0)))
        }
        else -> {
            null
        }
    }
}