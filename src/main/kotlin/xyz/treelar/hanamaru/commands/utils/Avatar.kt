package xyz.treelar.hanamaru.commands.utils

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.baseclasses.getUserParam

@Component
class Avatar: Command()
{
    init {
        name = "avatar"
    }
    override fun execute(event: CommandEvent) {
        event.getUserParam()?.run {
            event.reply(this.avatarUrl)
        }
    }
}