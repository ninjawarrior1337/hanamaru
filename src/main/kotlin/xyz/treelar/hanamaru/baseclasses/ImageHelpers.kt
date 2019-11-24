package xyz.treelar.hanamaru.baseclasses

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
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

fun BufferedImage.toInputStream(): InputStream {
    val os = ByteArrayOutputStream()
    ImageIO.write(this, "png", os)
    return ByteArrayInputStream(os.toByteArray())
}