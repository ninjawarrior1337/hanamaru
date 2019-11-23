package xyz.treelar.hanamaru.models

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ServerOptions(@Id val id: Long)
{
    var enableDio = true
    var enableShiraz = false
}