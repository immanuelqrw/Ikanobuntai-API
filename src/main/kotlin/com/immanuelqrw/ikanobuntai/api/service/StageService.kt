package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.StageService as UnitStageService

@Service
class StageService {

    @Autowired
    private lateinit var stageService: UnitStageService

    fun findByName(name: String): Stage? {
        val page = PageRequest.of(1, 1)

        return stageService.findAll(page, "name:$name").content.firstOrNull()
    }

}
