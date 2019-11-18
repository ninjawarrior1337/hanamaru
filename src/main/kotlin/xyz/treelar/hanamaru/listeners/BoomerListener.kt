package xyz.treelar.hanamaru.listeners

import com.jagrosh.jdautilities.command.Command
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.springframework.stereotype.Component

@Component
class BoomerListener : EventListener
{
    override fun onEvent(event: GenericEvent) {
        if(event is MessageReactionAddEvent)
        {
            if(event.reactionEmote.emoji == "\uD83D\uDCA5")
            {
                event.channel.retrieveMessageById(event.messageId).queue {
                    it.clearReactions().queue {
                        event.channel.addReactionById(event.messageId, "\uD83C\uDD97").queue() //ok
                        event.channel.addReactionById(event.messageId, "\uD83C\uDDE7").queue() //b
                        event.channel.addReactionById(event.messageId, "\uD83C\uDDF4").queue() //o
                        event.channel.addReactionById(event.messageId, "\uD83C\uDD7E️").queue() //o red
                        event.channel.addReactionById(event.messageId, "\uD83C\uDDF2").queue() //m
                        event.channel.addReactionById(event.messageId, "\uD83C\uDDEA").queue() //e
                        event.channel.addReactionById(event.messageId, "\uD83C\uDDF7").queue() //r
                    }
                }
            }
        }
    }
}