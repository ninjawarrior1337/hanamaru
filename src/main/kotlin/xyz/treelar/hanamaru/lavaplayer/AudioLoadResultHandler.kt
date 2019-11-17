package xyz.treelar.hanamaru.lavaplayer

import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack

class AudioLoadResultHandler(private val trackScheduler: TrackScheduler, private val event: CommandEvent): AudioLoadResultHandler {
    override fun loadFailed(exception: FriendlyException?) {
        event.reply("Could not load track, reason: $exception")
    }

    override fun trackLoaded(track: AudioTrack?) {
        if (track != null) {
            trackScheduler.queue(track)
            event.reply("${track.identifier} added!")
        }
    }

    override fun noMatches() {
        event.reply("Couldn't find any match for your query.")
    }

    override fun playlistLoaded(playlist: AudioPlaylist?) {
        if (playlist != null) {
            for(track in playlist.tracks)
                trackScheduler.queue(track)
        }
    }
}