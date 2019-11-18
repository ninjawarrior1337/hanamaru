package xyz.treelar.hanamaru.listeners

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.lavaplayer.PlayerStore
import xyz.treelar.hanamaru.lavaplayer.TrackSchedulerStore

@Component
class VCListener : EventListener
{
    @Autowired
    lateinit var playerStore: PlayerStore

    @Autowired
    lateinit var trackSchedulerStore: TrackSchedulerStore

    override fun onEvent(event: GenericEvent) {
        if(event is GuildVoiceLeaveEvent) {
            if(event.channelLeft.members.size == 1)
                if(event.guild.audioManager.isConnected)
                {
                    event.channelLeft.guild.audioManager.sendingHandler = null
                    event.channelLeft.guild.audioManager.closeAudioConnection()
                    playerStore[event.guild.idLong]?.destroy()
                    playerStore.remove(event.guild.idLong)

                    trackSchedulerStore.remove(event.guild.idLong)
                }
        }
    }
}