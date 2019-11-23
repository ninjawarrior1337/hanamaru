package xyz.treelar.hanamaru.listeners

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.sendAndQueue

@Component
class RemListener : EventListener
{
    final val remReplies = listOf(
        "https://vignette.wikia.nocookie.net/rezero/images/0/02/Rem_Anime.png/revision/latest?cb=20160730213532",
        "https://vignette.wikia.nocookie.net/rezero/images/4/48/Rem_-_Re_Zero_Anime_BD_-_3.png/revision/latest?cb=20160914132245",
        "https://pre00.deviantart.net/9b2d/th/pre/f/2016/149/c/b/tumblr_o6fxqn1obs1u0xk60o1_1280_by_sakamileo-da47099.png",
        "https://thehypedgeek.com/wp-content/uploads/2017/04/rem-re-zero.jpg",
        "レム",
        "Rem (レム) is one of the twin maids working for Roswaal L Mathers. Rem has medium length sky blue hair that covers her right eye, large light blue eyes, and young features. She also has hair clips...",
        "https://i.ytimg.com/vi/SGMogurjkCU/maxresdefault.jpg"
    )
    override fun onEvent(event: GenericEvent) {
        if(event is MessageReceivedEvent) {
            if(event.message.contentRaw.matches(Regex("(rem|best girl)(\$| )", setOf(RegexOption.IGNORE_CASE, RegexOption.MULTILINE))))
                event.channel.sendAndQueue(remReplies.random())
        }
    }
}