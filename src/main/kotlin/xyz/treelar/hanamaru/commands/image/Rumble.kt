package xyz.treelar.hanamaru.commands.image

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.baseclasses.getImage
import xyz.treelar.hanamaru.baseclasses.toPngInputStream
import javax.imageio.ImageIO

@Component
class Rumble: Command()
{
    init {
        name = "rumble"
        aliases = arrayOf("menacing")
    }

    val overlay = ImageIO.read(ClassPathResource("overlays/rumble.png").inputStream)
    override fun execute(event: CommandEvent) {
        event.getImage()?.let {image ->
            image.graphics.drawImage(overlay, 0, 0, image.width, image.height/2, null)
            event.channel.sendFile(image.toPngInputStream(), "image.png").queue()
        }
    }
}