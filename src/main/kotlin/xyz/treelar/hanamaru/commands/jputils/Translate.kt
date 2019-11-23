package xyz.treelar.hanamaru.commands.jputils

import com.gtranslate.Language
import com.gtranslate.Translator
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.stereotype.Component

@Component
class Translate : Command()
{
    private val translator = Translator.getInstance()
    init {
        name = "translate"
        aliases = arrayOf("trans")
    }
    override fun execute(event: CommandEvent) {
        if(event.args.isNotEmpty()) {
            event.reply(
                translator.translate(
                    event.args,
                    translator.detect(event.args),
                    Language.ENGLISH
                )
            )
        }
    }
}