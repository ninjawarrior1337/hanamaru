package xyz.treelar.hanamaru.baseclasses

import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.entities.User

fun MessageChannel.sendAndQueue(message: CharSequence) {
    this.sendMessage(message).queue()
}

fun CommandEvent.getUserParam(): User? {
    return when {
        this.message.mentionedUsers.getOrNull(0) != null -> {
            message.mentionedUsers[0]
        }
        this.args.isNotEmpty() -> {
            jda.getUserById(this.args)
        }
        else -> {
            this.author
        }
    }
}