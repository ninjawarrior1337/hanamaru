package xyz.treelar.hanamaru.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.stereotype.Component


@Component
class Ping : Command() {
    init {
        name = "ping"
    }
    override fun execute(event: CommandEvent?) {
        val time = System.currentTimeMillis()
        event!!.channel.sendMessage("Pong").queue {
            it.editMessage("Pong! (This took **${System.currentTimeMillis()-time} ms**)").queue()
        }
    }
}