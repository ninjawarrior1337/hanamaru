package xyz.treelar.hanamaru.commands.`fun`

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.internal.handle.GuildSetupController
import org.springframework.stereotype.Component

@Component
class Migrate : Command() {
    init {
        name = "migrate"
        aliases = arrayOf("mgr")
        userPermissions = arrayOf(Permission.VOICE_MOVE_OTHERS)
    }
    override fun execute(event: CommandEvent) {
        val gm = event.guild
        val executor = event.member
        if(!event.member.voiceState?.inVoiceChannel()!!) return event.reply("You must be in a voice channel")
        for (member in executor.voiceState?.channel?.members!!) {
            gm.moveVoiceMember(member, event.jda.getVoiceChannelById(event.args))
        }
    }
}