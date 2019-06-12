package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.core.api.service.BaseUniqueService
import com.immanuelqrw.ikanobuntai.api.entity.Move
import org.springframework.stereotype.Service

@Service
class MoveService : BaseUniqueService<Move>() {

    fun findByName(name: String): Move? {
        return findAll("name:$name").firstOrNull()
    }

}
