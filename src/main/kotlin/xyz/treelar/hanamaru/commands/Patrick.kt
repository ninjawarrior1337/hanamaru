package xyz.treelar.hanamaru.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import kotlin.math.abs

@Component
class Patrick : Command() {
    val imagesList = listOf(
            ClassPathResource("patrick/0.jpg"),
            ClassPathResource("patrick/1.jpg"),
            ClassPathResource("patrick/2.jpg")
    )
    val imagesListReverse = listOf(
            ClassPathResource("patrick/0r.jpg"),
            ClassPathResource("patrick/1r.jpg"),
            ClassPathResource("patrick/2r.jpg")
    )
    init {
        name = "patrick"
        help = "I think we all know what this does"
        arguments = "<number of patricks>"
    }
    override fun execute(event: CommandEvent) {
        val number = event.args.toInt()
        when(number) {
            in 0..3 -> {
                event.reply(imagesList[0].file, imagesList[0].filename)
                for(x in 1..number) {
                    event.reply(imagesList[1].file, imagesList[1].filename)
                }
                event.reply(imagesList[2].file, imagesList[2].filename)
            }
            in -3..-1 -> {
                event.reply(imagesListReverse[0].file, imagesListReverse[0].filename)
                for(x in 1..abs(number)) {
                    event.reply(imagesListReverse[1].file, imagesListReverse[1].filename)
                }
                event.reply(imagesListReverse[2].file, imagesListReverse[2].filename)
            }
            else -> event.reply("$number is too big, please make it smaller.")
        }
    }
}