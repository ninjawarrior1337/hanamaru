package xyz.treelar.hanamaru.commands.music

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.lavaplayer.PlayerStore

@Component
class Volume : Command()
{
    @Autowired
    lateinit var playerStore: PlayerStore

    init {
        name = "volume"
        aliases = arrayOf("vol")
    }

    override fun execute(event: CommandEvent) {
        playerStore[event.channel.idLong]?.let {
            it.volume = event.args.toInt()
            event.reply("Volume set to ${it.volume}")
        }
    }
}