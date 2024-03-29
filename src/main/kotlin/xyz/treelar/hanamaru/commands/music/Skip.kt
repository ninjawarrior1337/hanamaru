package xyz.treelar.hanamaru.commands.music

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.lavaplayer.TrackSchedulerStore

@Component
class Skip : Command()
{
    init {
        name = "skip"
    }
    @Autowired
    lateinit var trackSchedulerStore: TrackSchedulerStore

    override fun execute(event: CommandEvent) {
        trackSchedulerStore[event.guild.idLong]?.nextTrack()
    }
}