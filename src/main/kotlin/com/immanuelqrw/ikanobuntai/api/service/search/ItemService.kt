package com.immanuelqrw.ikanobuntai.api.service.search

import com.immanuelqrw.ikanobuntai.api.entity.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.immanuelqrw.ikanobuntai.api.service.unit.ItemService as UnitItemService

@Service
class ItemService {

    @Autowired
    private lateinit var itemService: UnitItemService

    fun findByName(name: String): Item? {
        return itemService.findAll("name:$name").firstOrNull()
    }

}
