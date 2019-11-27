package xyz.treelar.hanamaru.commands.utils

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.MessageEmbed
import org.springframework.stereotype.Component

@Component
class ServerInfo: Command()
{
    init {
        name = "serverinfo"
        aliases = arrayOf("server")
    }
    override fun execute(event: CommandEvent) {
        val server = event.guild
        event.reply(constructMessage(server))
    }

    fun constructMessage(server: Guild): MessageEmbed {
        return EmbedBuilder().run {
            setTitle("Information about ${server.name}")
            setThumbnail(server.iconUrl)
            fields.addAll(generateFields(server))
            build()
        }
    }

    fun generateFields(server: Guild): List<MessageEmbed.Field> {
        return listOf(
                MessageEmbed.Field("Server name", server.name, true),
                MessageEmbed.Field("Server ID", server.id, true),
                MessageEmbed.Field("Members", server.members.size.toString(), true),
                MessageEmbed.Field("Bots", server.members.filter { it.user.isBot }.size.toString(), true),
                MessageEmbed.Field("Owner", "${server.owner?.effectiveName}#${server.owner?.user?.discriminator}", true),
                MessageEmbed.Field("Region", server.regionRaw, true),
                MessageEmbed.Field("Created", server.timeCreated.toLocalDateTime().toString(), true)
        )
    }
}