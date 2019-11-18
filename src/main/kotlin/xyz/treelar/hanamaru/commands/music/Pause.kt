package xyz.treelar.hanamaru.commands.music

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.lavaplayer.PlayerStore

@Component
class Pause : Command()
{
    init {
        name = "pause"
    }

    @Autowired
    lateinit var playerStore: PlayerStore

    override fun execute(event: CommandEvent?) {
        playerStore[event?.guild?.idLong]?.isPaused = true
    }
}