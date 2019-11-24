package xyz.treelar.hanamaru.models

import javax.persistence.*

@Entity
data class ServerOptions(@Id val id: Long)
{
    @ElementCollection(fetch = FetchType.EAGER)
    var options: MutableMap<String, String> = HashMap()
}