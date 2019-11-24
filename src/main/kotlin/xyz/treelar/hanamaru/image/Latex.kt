package xyz.treelar.hanamaru.image

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.MessageBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import org.scilab.forge.jlatexmath.TeXConstants
import org.scilab.forge.jlatexmath.TeXFormula
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.baseclasses.toBufferedImage
import xyz.treelar.hanamaru.baseclasses.toInputStream
import java.awt.Color
import java.io.InputStream
import javax.imageio.ImageIO

@Component
class Latex : Command()
{
    init {
        name = "latex"
        aliases = arrayOf("tex")
    }

    override fun execute(event: CommandEvent) {
        if(event.args.isNotBlank()) {
            event.channel.sendFile(render(event.args), "image.png").queue()
        }
    }

    fun render(tex: String): InputStream {
        val formula = TeXFormula(tex)
        val outputImage = formula.createBufferedImage(TeXConstants.STYLE_DISPLAY, 30f, Color.BLACK, Color.WHITE)
        return outputImage.toBufferedImage().toInputStream()
    }
}