package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Format
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.FormatService as UnitFormatService

@Service
class FormatService {

    @Autowired
    private lateinit var formatService: UnitFormatService

    fun findByName(name: String): Format? {
        val page = PageRequest.of(1, 1)

        return formatService.findAll(page, "name:$name").content.firstOrNull()
    }

}
