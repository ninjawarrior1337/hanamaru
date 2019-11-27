package xyz.treelar.hanamaru.listeners

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.commands.utils.ServerInfo
import xyz.treelar.hanamaru.commands.utils.UserInfo

@Component
class FrickYouJaxonsServer: EventListener
{
    override fun onEvent(event: GenericEvent) {
        if(event is MessageReceivedEvent) {
            if(event.message.contentRaw.startsWith("+server")) {
                event.channel.sendMessage(ServerInfo().constructMessage(event.guild)).queue()
            }
            if(event.message.contentRaw.startsWith("+user")) {
                event.message.mentionedUsers.firstOrNull().let {
                    event.channel.sendMessage(UserInfo().constructMessage(event.guild.getMember(it ?: event.author)!!)).queue()
                }
            }
        }
    }
}