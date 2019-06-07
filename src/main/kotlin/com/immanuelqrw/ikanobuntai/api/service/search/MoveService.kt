package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Move
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.MoveService as UnitMoveService

@Service
class MoveService {

    @Autowired
    private lateinit var moveService: UnitMoveService

    fun findByName(name: String): Move? {
        return moveService.findAll("name:$name").firstOrNull()
    }

}
