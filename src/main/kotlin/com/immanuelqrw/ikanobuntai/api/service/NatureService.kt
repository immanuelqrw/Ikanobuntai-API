package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Nature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.NatureService as UnitNatureService

@Service
class NatureService {

    @Autowired
    private lateinit var natureService: UnitNatureService

    fun findByName(name: String): Nature? {
        val page = PageRequest.of(1, 1)

        return natureService.findAll(page, "name:$name").content.firstOrNull()
    }

}
