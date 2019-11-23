package xyz.treelar.hanamaru.commands.jputils

import com.atilika.kuromoji.ipadic.Tokenizer
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.moji4j.MojiConverter
import org.springframework.stereotype.Component

@Component
class Romanize : Command()
{
    init {
        name = "romanize"
        aliases = arrayOf("roma")
    }
    val converter = MojiConverter()
    val tokenizer = Tokenizer.Builder().build()

    override fun execute(event: CommandEvent) {
        var finalString = ""
        for(token in tokenizer.tokenize(event.args)) {
            finalString += token.pronunciation + " "
        }
        event.reply(converter.convertKanaToRomaji(finalString.substring(0, finalString.length-1)))
    }
}