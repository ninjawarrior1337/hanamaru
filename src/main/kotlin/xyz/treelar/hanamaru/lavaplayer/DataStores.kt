package xyz.treelar.hanamaru.lavaplayer

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import net.dv8tion.jda.api.entities.Guild
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PlayerStore : HashMap<Long, AudioPlayer>()
{
    @Autowired
    lateinit var playerManager: AudioPlayerManager

    fun getOrCreateIfDoesntExist(guild: Guild): AudioPlayer {
        val serverId = guild.id.toLong()
        return if(this[serverId] != null)
            this[serverId]!!
        else
        {
            this[serverId] = playerManager.createPlayer()
            this[serverId]!!
        }
    }
}

@Component
class TrackSchedulerStore : HashMap<Long, TrackScheduler>() {
    fun getOrCreateIfDoesntExist(guild: Guild, player: AudioPlayer): TrackScheduler {
        val serverId = guild.id.toLong()
        return if(this[serverId] != null)
            this[serverId]!!
        else
        {
            this[serverId] = TrackScheduler(player)
            player.addListener(this[serverId])
            this[serverId]!!
        }
    }
}