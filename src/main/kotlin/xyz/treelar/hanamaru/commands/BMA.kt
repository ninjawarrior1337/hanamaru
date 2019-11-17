package xyz.treelar.hanamaru.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.audio.AudioSendHandler
import net.dv8tion.jda.api.managers.AudioManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.lavaplayer.*

@Component
class BMA : Command()
{
    init {
        name = "bma"
    }
    override fun execute(event: CommandEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}