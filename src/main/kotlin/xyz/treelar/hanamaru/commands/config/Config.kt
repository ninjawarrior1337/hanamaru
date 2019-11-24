package xyz.treelar.hanamaru.commands.config

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import xyz.treelar.hanamaru.models.ServerOptions
import xyz.treelar.hanamaru.repos.ServerOptionsRepo

@Component
class Config: Command() {
    init {
        name = "config"
        ownerCommand = true
    }
    @Autowired
    lateinit var serverOptionsRepo: ServerOptionsRepo

    override fun execute(event: CommandEvent) {
        val guildId = event.guild.idLong

        val optionName = event.args.split(" ")[0]
        val newValue = event.args.split(" ")[1]

        if(!serverOptionsRepo.existsById(guildId)) {
            serverOptionsRepo.save(ServerOptions(guildId))
        }
        val guildSettings = serverOptionsRepo.findByIdOrNull(guildId)
        if (guildSettings != null) {
            guildSettings.options[optionName] = newValue
            serverOptionsRepo.save(guildSettings)
        }
        event.reply("$optionName is now **${serverOptionsRepo.findByIdOrNull(guildId)?.options?.get(optionName)}**")
    }
}