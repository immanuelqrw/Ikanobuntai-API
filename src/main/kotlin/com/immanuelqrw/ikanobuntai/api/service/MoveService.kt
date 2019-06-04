package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.UNIQUE_PAGE_REQUEST
import com.immanuelqrw.ikanobuntai.api.entity.Move
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.MoveService as UnitMoveService

@Service
class MoveService {

    @Autowired
    private lateinit var moveService: UnitMoveService

    fun findByName(name: String): Move? {
        return moveService.findAll(UNIQUE_PAGE_REQUEST, "name:$name").content.firstOrNull()
    }

}
