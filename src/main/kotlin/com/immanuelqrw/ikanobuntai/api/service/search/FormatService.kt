package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Format
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.FormatService as UnitFormatService

@Service
class FormatService {

    @Autowired
    private lateinit var formatService: UnitFormatService

    fun findByName(name: String): Format? {
        return formatService.findAll("name:$name").firstOrNull()
    }

}
