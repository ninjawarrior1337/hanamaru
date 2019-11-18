package xyz.treelar.hanamaru.commands.admin

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import org.springframework.stereotype.Component
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.script.ScriptEngineManager


@Component
class Eval : Command()
{
    init {
        name = "eval"
        ownerCommand = true
    }
    override fun execute(event: CommandEvent?) {
        val scriptEngine = ScriptEngineManager().getEngineByName("nashorn")
        val bindings = scriptEngine.createBindings()
        bindings["event"] = event

        val pattern: Pattern = Pattern.compile("```(.+)```", Pattern.DOTALL)
        val matcher: Matcher = pattern.matcher(event?.message?.contentRaw!!)

        if(matcher.find())
            scriptEngine.eval(matcher.group(1), bindings)
        else
            scriptEngine.eval(event.args, bindings)
    }
}