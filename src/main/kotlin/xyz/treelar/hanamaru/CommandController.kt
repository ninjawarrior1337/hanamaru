package xyz.treelar.hanamaru

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandClient
import com.jagrosh.jdautilities.command.CommandClientBuilder
import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.entities.Activity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class CommandController
{
    final val dotenv = dotenv()
    val commandBuilder = CommandClientBuilder()

    lateinit var commandClient: CommandClient

    @Autowired
    lateinit var commands: Array<Command>

    @PostConstruct
    fun build() {
        commandBuilder.addCommands(*commands)
        commandBuilder.setOwnerId(dotenv["OWNER"])
        commandBuilder.setPrefix("*")
        commandBuilder.setActivity(Activity.playing("LLSIFAS"))
        commandClient = commandBuilder.build()
    }
}