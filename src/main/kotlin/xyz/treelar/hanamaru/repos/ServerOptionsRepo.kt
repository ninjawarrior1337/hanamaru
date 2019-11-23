package xyz.treelar.hanamaru.repos

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import xyz.treelar.hanamaru.models.ServerOptions

interface ServerOptionsRepo: CrudRepository<ServerOptions, Long>
{
//    @Modifying
//    @Query("update ServerOptions s set u.?1 = ?2,  where s.id = ?3 ")
//    fun setOptionById(optionName: String, newValue: Any, serverId: Long)
}