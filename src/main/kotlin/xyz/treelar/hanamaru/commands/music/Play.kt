package xyz.treelar.hanamaru.commands.music

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.lavaplayer.*

@Component
class Play: Command() {
    init {
        name = "play"
    }
    @Autowired
    lateinit var playerStore: PlayerStore

    @Autowired
    lateinit var trackSchedulerStore: TrackSchedulerStore

    @Autowired
    lateinit var playerManager: AudioPlayerManager

    override fun execute(event: CommandEvent) {
        val player = playerStore.getOrCreateIfDoesntExist(event.guild)
        val trackScheduler = trackSchedulerStore.getOrCreateIfDoesntExist(event.guild, player)
        val audioManager = event.guild.audioManager

        if(event.member.voiceState !== null) {
            audioManager.openAudioConnection(event.member.voiceState?.channel)
            audioManager.sendingHandler = LavaPlayerSendHandler(player)

            playerManager.loadItem(event.args, AudioLoadResultHandler(trackScheduler, event))

            audioManager.
        }
    }
}