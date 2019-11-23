package xyz.treelar.hanamaru.baseclasses

import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Indexed
import java.lang.annotation.Inherited

@InheritedComponent
interface HanamaruEventListener : EventListener {

}