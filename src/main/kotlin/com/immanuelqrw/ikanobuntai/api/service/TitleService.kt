package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Title
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.TitleService as UnitTitleService

@Service
class TitleService {

    @Autowired
    private lateinit var titleService: UnitTitleService

    fun findByName(name: String): Title? {
        val page = PageRequest.of(1, 1)

        return titleService.findAll(page, "name:$name").content.firstOrNull()
    }

}
