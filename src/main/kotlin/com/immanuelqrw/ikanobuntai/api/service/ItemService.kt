package com.immanuelqrw.ikanobuntai.api.service

import com.immanuelqrw.ikanobuntai.api.entity.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.ItemService as UnitItemService

@Service
class ItemService {

    @Autowired
    private lateinit var itemService: UnitItemService

    fun findByName(name: String): Item? {
        val page = PageRequest.of(1, 1)

        return itemService.findAll(page, "name:$name").content.firstOrNull()
    }

}
