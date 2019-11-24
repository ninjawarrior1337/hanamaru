package xyz.treelar.hanamaru.baseclasses

import net.dv8tion.jda.api.entities.MessageChannel

fun MessageChannel.sendAndQueue(message: CharSequence) {
    this.sendMessage(message).queue()
}
