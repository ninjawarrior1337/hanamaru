package xyz.treelar.hanamaru.commands.utils

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.MessageEmbed
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
class UserInfo : Command()
{
    init {
        name = "userinfo"
        aliases = arrayOf("user")
    }
    override fun execute(event: CommandEvent) {
        val member = event.message.mentionedMembers.firstOrNull() ?: event.member

        event.reply(constructMessage(member))
    }

    fun constructMessage(member: Member): MessageEmbed {
        return EmbedBuilder().run {
            setThumbnail(member.user.avatarUrl)
            setTitle("About ${member.user.id}")
            fields.add(0, MessageEmbed.Field("Full name", "${member.user.name}#${member.user.discriminator}", true))
            fields.add(1, MessageEmbed.Field("Nickname", member.nickname.let { if(it.isNullOrBlank()) "None" else it }, true))
            fields.add(2, MessageEmbed.Field("Account Created", member.timeCreated.run {
                toLocalDateTime()
                format(DateTimeFormatter.ofPattern("EEE MMM d uuuu @ h:m:s O"))
            }, true))
            fields.add(3, MessageEmbed.Field("Joined This Server", member.timeJoined.run {
                toLocalDateTime()
                format(DateTimeFormatter.ofPattern("EEE MMM d uuuu @ h:m:s O"))
            }, true))
            build()
        }
    }
}