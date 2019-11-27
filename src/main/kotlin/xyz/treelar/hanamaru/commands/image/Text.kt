package xyz.treelar.hanamaru.commands.image

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.stereotype.Component
import java.awt.Graphics2D

@Component
class Text: Command()
{
    init {
        name = "text"
    }
    override fun execute(event: CommandEvent) {

    }
}