package xyz.treelar.hanamaru.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.Permission
import org.springframework.stereotype.Component

@Component
class Invite: Command() {
    init {
        name = "invite"
        guildOnly = false
    }
    override fun execute(event: CommandEvent?) {
        event?.reply("Invite me at ${event.jda.getInviteUrl(Permission.ADMINISTRATOR)}")
    }
}