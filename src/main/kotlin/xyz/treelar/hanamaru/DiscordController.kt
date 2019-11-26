package xyz.treelar.hanamaru

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.sedmelluq.discord.lavaplayer.jdaudp.NativeAudioSendFactory
import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.hooks.EventListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import xyz.treelar.hanamaru.listeners.MessageListener
import javax.annotation.PostConstruct

@Component
class DiscordController {
    final var dotenv = dotenv()

    final val builder = JDABuilder(dotenv["TOKEN"])
    lateinit var jda: JDA

    @Autowired
    lateinit var eventListeners: Array<EventListener>

    @Autowired
    lateinit var commandController: CommandController

    @PostConstruct
    fun buildJDA() {
        builder.addEventListeners(commandController.commandClient)
        builder.addEventListeners(*eventListeners)
        builder.setAudioSendFactory(NativeAudioSendFactory())
        jda = builder.build()
    }
}