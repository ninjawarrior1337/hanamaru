package xyz.treelar.hanamaru.listeners

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.springframework.stereotype.Component

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
}