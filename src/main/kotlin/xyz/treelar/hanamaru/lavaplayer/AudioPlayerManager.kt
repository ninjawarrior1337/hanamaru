package xyz.treelar.hanamaru.lavaplayer

import com.sedmelluq.discord.lavaplayer.container.MediaContainerRegistry
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class AudioPlayerManager: DefaultAudioPlayerManager()
{
    @PostConstruct
    fun postConstruct() {
        AudioSourceManagers.registerLocalSource(this)
        AudioSourceManagers.registerRemoteSources(this)
    }
}