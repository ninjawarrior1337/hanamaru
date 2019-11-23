package xyz.treelar.hanamaru.baseclasses

import org.springframework.stereotype.Component
import java.lang.annotation.Inherited

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@Component
annotation class InheritedComponent
{

}