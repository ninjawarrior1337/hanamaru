package xyz.treelar.hanamaru.listeners

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.languagetool.JLanguageTool
import org.languagetool.language.AmericanEnglish
import org.languagetool.rules.spelling.SpellingCheckRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.baseclasses.HanamaruEventListener
import xyz.treelar.hanamaru.repos.ServerOptionsRepo

@Component
class ShirazListener : EventListener {
    private final val lt: JLanguageTool = JLanguageTool(AmericanEnglish())

    @Autowired
    lateinit var sor: ServerOptionsRepo

    init {
        for(rule in lt.allActiveRules) {
            val wordsToIgnore = listOf("i", "bruh")
            if(rule is SpellingCheckRule)
                rule.addIgnoreTokens(wordsToIgnore)
        }
    }
    override fun onEvent(event: GenericEvent) {
        if(event is MessageReceivedEvent) {
            sor.findByIdOrNull(event.channel.idLong)?.let {
                if(it.enableShiraz) {
                    if (event.author.idLong == 212335473887019008) {
                        val matches = lt.check(event.message.contentStripped)
                        var finalSpellCorrectedMessage = event.message.contentRaw
                        //                for(match in matches) {
                        //                    if(match.suggestedReplacements.firstOrNull() != null) {
                        //                        event.channel.sendMessage(match.suggestedReplacements.first()).queue()
                        //                    }
                        //                }
                        for (match in matches) {
                            if (match.suggestedReplacements.firstOrNull() != null) {
                                finalSpellCorrectedMessage = finalSpellCorrectedMessage.replace(event.message.contentStripped.substring(match.fromPos, match.toPos), "*${match.suggestedReplacements[0]} ")
                            }
                        }
                        if (matches.size > 0) {
                            event.channel.sendMessage("*${finalSpellCorrectedMessage}").queue()
                        }
                    }
                }
            }
        }
    }
}