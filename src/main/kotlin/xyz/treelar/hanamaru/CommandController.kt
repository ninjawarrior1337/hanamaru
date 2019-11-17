package xyz.treelar.hanamaru

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandClient
import com.jagrosh.jdautilities.command.CommandClientBuilder
import net.dv8tion.jda.api.entities.Activity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class CommandController
{
    val commandBuilder = CommandClientBuilder()

    lateinit var commandClient: CommandClient

    @Autowired
    lateinit var commands: Array<Command>

    @PostConstruct
    fun build() {
        commandBuilder.addCommands(*commands)
        commandBuilder.setOwnerId("102939281470816256")
        commandBuilder.setPrefix("*")
        commandBuilder.setActivity(Activity.playing("LLSIFAS"))
        commandClient = commandBuilder.build()
    }
}