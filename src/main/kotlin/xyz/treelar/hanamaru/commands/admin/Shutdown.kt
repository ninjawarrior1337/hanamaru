package xyz.treelar.hanamaru.commands.admin

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.entities.Emote
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.internal.requests.Route
import org.springframework.stereotype.Component
import java.util.*
import kotlin.system.exitProcess

@Component
class Shutdown : Command()
{
    init {
        name = "shutdown"
        ownerCommand = true
    }
    override fun execute(event: CommandEvent?) {
        event?.reply("Are you sure you want to do this, React if you are sure.") {
            it.addReaction("\uD83D\uDC4D").queue()
            event.jda.addEventListener(object : EventListener {
                override fun onEvent(messageEvent: GenericEvent) {
                    if(messageEvent is MessageReactionAddEvent) {
                        if(messageEvent.user == event.author) {
                            it.editMessage("**Shutting Down...**").queue()
                            it.clearReactions().queue()
                            exitProcess(0)
                        }
                    }
                }
            })
        }
    }
}