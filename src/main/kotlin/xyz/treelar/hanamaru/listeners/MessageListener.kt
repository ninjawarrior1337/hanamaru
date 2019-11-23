package xyz.treelar.hanamaru.listeners

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.baseclasses.HanamaruEventListener
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.imageio.ImageIO

@Component
class MessageListener: EventListener
{
    override fun onEvent(event: GenericEvent) {
        if(event is MessageReceivedEvent && !event.message.author.isBot) {
            if(event.message.contentRaw.contains("derek"))
                event.message reply "hello this is derek, thank you for calling, I cannot pick up the phone right now..."
        }
    }

    infix fun Message.reply(replyMessage: String) {
        this.channel.sendMessage(replyMessage).queue()
    }

    fun BufferedImage.asInputStream(): InputStream {
        val os = ByteArrayOutputStream()
        ImageIO.write(this, "png", os)
        return ByteArrayInputStream(os.toByteArray())
    }
}