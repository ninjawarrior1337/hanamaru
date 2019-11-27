package xyz.treelar.hanamaru.commands.image

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.scilab.forge.jlatexmath.TeXConstants
import org.scilab.forge.jlatexmath.TeXFormula
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.baseclasses.toBufferedImage
import xyz.treelar.hanamaru.baseclasses.toPngInputStream
import java.awt.Color
import java.io.InputStream

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
        return outputImage.toBufferedImage().toPngInputStream()
    }
}