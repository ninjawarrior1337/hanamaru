package xyz.treelar.hanamaru.commands.music

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.lavaplayer.PlayerStore
import xyz.treelar.hanamaru.lavaplayer.TrackSchedulerStore

@Component
class Leave : Command()
{
    init {
        name = "leave"
    }
    @Autowired
    lateinit var playerStore: PlayerStore

    @Autowired
    lateinit var trackSchedulerStore: TrackSchedulerStore

    override fun execute(event: CommandEvent) {
        event.guild.audioManager.sendingHandler = null
        event.guild.audioManager.closeAudioConnection()
        playerStore[event.guild.idLong]?.destroy()
        playerStore.remove(event.guild.idLong)

        trackSchedulerStore.remove(event.guild.idLong)
    }
}